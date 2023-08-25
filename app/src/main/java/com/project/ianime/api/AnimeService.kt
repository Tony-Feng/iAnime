package com.project.ianime.api

import com.project.ianime.api.model.AnimeListApiModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface AnimeService {

    //TODO: 08-15 exact api and header for get anime
    @GET("")
    fun getAnimeList(): Single<AnimeListApiModel>

}