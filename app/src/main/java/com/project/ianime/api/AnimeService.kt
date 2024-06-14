package com.project.ianime.api

import com.project.ianime.api.model.AnimeListApiModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * API endpoints from the network
 */
interface AnimeService {

    @GET("animes")
    fun getAnimeListFromNetwork(): Single<AnimeListApiModel>

}