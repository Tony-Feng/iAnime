package com.project.ianime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ianime.api.ConnectionException
import com.project.ianime.api.NotFoundException
import com.project.ianime.api.UnauthorizedException
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.error.ErrorType
import com.project.ianime.api.model.AnimeApiModel
import com.project.ianime.repository.AnimeDataRepository
import com.project.ianime.screens.stateholder.AnimeUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val repository: AnimeDataRepository) : ViewModel() {

    private val _animeGalleryList = MutableLiveData<List<AnimeGalleryItem>>()
    val animeGalleryList: LiveData<List<AnimeGalleryItem>> = _animeGalleryList

    var animeUiState = MutableLiveData<AnimeUiState>()

    var animeTargetDetails = MutableLiveData<AnimeApiModel>()

    private val viewScopeSubscriptionTracker = CompositeDisposable()

    init {
        animeUiState.postValue(AnimeUiState.Success)
    }

    /**
     * get list of anime gallery items
     */
    fun getAnimeGalleryList() {
        repository.getGalleryList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ galleryItems ->
                if (galleryItems.isEmpty()) {
                    animeUiState.postValue(AnimeUiState.Empty)
                } else {
                    animeUiState.postValue(AnimeUiState.Success)
                    _animeGalleryList.postValue(galleryItems)
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
    fun getAnimeById(animeTargetId: String){
        repository.getAnimeListFromNetwork()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ animeList ->
                val targetDetails = animeList.find {
                    it.animeId == animeTargetId
                }
                animeTargetDetails.postValue(targetDetails!!)
            }, {
                handleError(it)
            }
            )
            .also { viewScopeSubscriptionTracker.add(it) }
    }

    private fun handleError(exception: Throwable){
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
            else -> animeUiState.postValue(AnimeUiState.Error(ErrorType.GENERIC))
        }
    }

}