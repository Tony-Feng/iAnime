package com.project.ianime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ianime.api.error.*
import com.project.ianime.api.model.AnimeApiModel
import com.project.ianime.repository.AnimeDataRepository
import com.project.ianime.screens.stateholder.AnimeUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class AnimeViewModel @Inject constructor(private val repository: AnimeDataRepository) : ViewModel() {

    private val _animeList = MutableLiveData<List<AnimeApiModel>>()
    val animeList: LiveData<List<AnimeApiModel>> = _animeList

    var animeUiState = MutableLiveData<AnimeUiState>()

    private val viewScopeSubscriptionTracker = CompositeDisposable()

    init {
        // refresh list of anime data when app restarts
        getAnimeList(true)
    }

    /**
     * get entire list of animes with all information
     */
    fun getAnimeList(refresh: Boolean = false) {
        if (refresh){
            getAnimeListFromNetwork()
        }
    }

    /**
     * get entire list of animes from the network
     */
    private fun getAnimeListFromNetwork(){
        animeUiState.postValue(AnimeUiState.Loading)
        repository.getAnimeListFromNetwork()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ animeItems ->
                if (animeItems.isEmpty()) {
                    animeUiState.postValue(AnimeUiState.Empty)
                } else {
                    animeUiState.postValue(AnimeUiState.Success)
                    _animeList.postValue(animeItems)
                }
            }, {
                handleError(it)
            }
            ).also { viewScopeSubscriptionTracker.add(it) }
    }

    /**
     * get an anime with specific id
     * @param animeId - anime target id
     */
    fun getAnimeById(animeTargetId: String): AnimeApiModel? {
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