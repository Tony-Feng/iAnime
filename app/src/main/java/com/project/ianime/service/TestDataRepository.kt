package com.project.ianime.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.ianime.api.model.AnimeApiModel
import com.project.ianime.api.model.AnimeCountryApiModel
import com.project.ianime.api.model.AnimeGenreApiModel
import com.project.ianime.api.model.AnimeStatusApiModel

class TestDataRepository {

    private val _testAnimeList = MutableLiveData<List<AnimeApiModel>>()

    val testAnimeList: LiveData<List<AnimeApiModel>> = _testAnimeList

    fun loadAnimeList() {
        val animeNameList = listOf("A", "B", "C", "D", "E", "F", "G")
        val dataList = animeNameList.map {
            AnimeApiModel("01", "完美世界", 10.0F, AnimeStatusApiModel.IN_PROGRESS, AnimeCountryApiModel.CHN_ANIME, AnimeGenreApiModel.LEGEND, "2021", "", null)
        }
        _testAnimeList.value = dataList
    }

}