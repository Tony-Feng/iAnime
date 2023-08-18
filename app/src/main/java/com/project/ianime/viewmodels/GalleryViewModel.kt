package com.project.ianime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ianime.api.EntitlementException
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.repository.AnimeDataRepository
import com.project.ianime.screens.stateholder.AnimeUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val repository: AnimeDataRepository) :
    ViewModel() {

    private val _animeGalleryList = MutableLiveData<List<AnimeGalleryItem>>()
    val animeGalleryList: LiveData<List<AnimeGalleryItem>> = _animeGalleryList

    lateinit var animeUiState: MutableLiveData<AnimeUiState>

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
                when (it) {
                    is EntitlementException -> {
                        animeUiState.postValue(AnimeUiState.Error(""))
                    }
                }
            }

            ).also { }
    }

}