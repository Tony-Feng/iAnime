package com.project.ianime.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.ianime.R
import com.project.ianime.model.AnimeItemUiState

class TestDataRepository {

    private val _testAnimeList = MutableLiveData<List<AnimeItemUiState>>()

    val testAnimeList: LiveData<List<AnimeItemUiState>> = _testAnimeList

    fun loadAnimeList(){
        val animeNameList = listOf("A", "B", "C", "D", "E", "F", "G")
        val dataList = animeNameList.map {
            AnimeItemUiState(R.drawable.ic_gallery, 10.0, it, "Some description ...")
        }
        _testAnimeList.value = dataList
    }

}