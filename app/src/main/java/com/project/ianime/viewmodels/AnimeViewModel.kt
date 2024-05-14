package com.project.ianime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.error.*
import com.project.ianime.api.model.AnimeApiModel
import com.project.ianime.data.AnimeEntity
import com.project.ianime.repository.AnimeDataRepository
import com.project.ianime.screens.stateholder.AnimeUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeViewModel @Inject constructor(private val repository: AnimeDataRepository) : ViewModel() {

    private val _animeList = MutableLiveData<List<AnimeApiModel>>()
    val animeList: LiveData<List<AnimeApiModel>> = _animeList

    var animeUiState = MutableLiveData<AnimeUiState>()

    var animeTargetDetails = MutableLiveData<AnimeApiModel>()

    private val viewScopeSubscriptionTracker = CompositeDisposable()

    init {
        //TODO: remove based on the actual response [for mock purpose only]
        animeUiState.postValue(AnimeUiState.Success)
    }

    /**
     * load entire anime list with refresh control
     */
    fun loadAnimeList(refresh: Boolean){
        if (refresh){
            // clear all the data from local storage first
            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    repository.clearOfflineAnimeList()
                }
            }
            loadAnimeListFromNetwork()
        } else {
            loadAnimeListFromLocalStorage()
        }
    }

    /**
     * get entire list of animes with all information from the network
     */
    private fun loadAnimeListFromNetwork() {
        repository.getAnimeListFromNetwork()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ animeItems ->
                if (animeItems.isEmpty()) {
                    animeUiState.postValue(AnimeUiState.Empty)
                } else {
                    animeUiState.postValue(AnimeUiState.Success)
                    _animeList.postValue(animeItems)

                    // load current data into database
                    viewModelScope.launch {
                        withContext(Dispatchers.IO){
                            animeItems.forEach{ anime ->
                                val eachAnimeEntity = anime.mapToAnimeEntity()
                                repository.insertAnimeIntoDatabase(eachAnimeEntity)
                            }
                        }
                    }
                }
            }, {
                handleError(it)
            }
            ).also { viewScopeSubscriptionTracker.add(it) }
    }

    /**
     * get anime details from the local storage
     */
    private fun loadAnimeListFromLocalStorage(){
        val animeOfflineData = mutableListOf<AnimeApiModel>()
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getOfflineAnimeList().collect { animeEntityList ->
                    animeEntityList.forEach {
                        animeOfflineData.add(it.mapToAnimeItem())
                    }
                }
            }
        }
        _animeList.postValue(animeOfflineData)
    }

    /**
     * get an anime with specific id
     * @param animeId - anime target id
     */
    fun getAnimeById(animeTargetId: String): AnimeGalleryItem? {
        return repository.getAnimeDetailsById(animeTargetId)
    }

    private fun handleError(exception: Throwable) {
        when (exception) {
            is UnauthorizedException -> {
                animeUiState.postValue(AnimeUiState.Error(ErrorType.UNAUTHORIZED))
            }
            is NotFoundException -> {
                animeUiState.postValue((AnimeUiState.Error(ErrorType.NOT_FOUND)))
            }
            is ConnectionException -> {
                animeUiState.postValue(AnimeUiState.Error(ErrorType.CONNECTION))
            }
            is BadRequestException -> {
                animeUiState.postValue(AnimeUiState.Error(ErrorType.BAD_REQUEST))
            }
            else -> animeUiState.postValue(AnimeUiState.Error(ErrorType.GENERIC))
        }
    }

}