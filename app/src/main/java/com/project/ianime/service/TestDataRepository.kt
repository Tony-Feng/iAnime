package com.project.ianime.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.ianime.api.data.AnimeGalleryItem

class TestDataRepository {

    private val _testAnimeList = MutableLiveData<List<AnimeGalleryItem>>()

    val testAnimeList: LiveData<List<AnimeGalleryItem>> = _testAnimeList

    fun loadAnimeList() {
        val animeNameList = listOf("A", "B", "C", "D", "E", "F", "G")
        val dataList = animeNameList.map {
            AnimeGalleryItem("01", "R.drawable.ic_gallery", 10.0F, it, "Some description ...")
        }
        _testAnimeList.value = dataList
    }

}