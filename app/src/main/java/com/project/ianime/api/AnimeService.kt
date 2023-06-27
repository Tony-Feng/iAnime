package com.project.ianime.api

import com.project.ianime.api.model.Anime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun setAnimeService(): AnimeService{
    val retrofit = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(AnimeService::class.java)

}


interface AnimeService {

    @GET("/data/2.5/weather")
    fun getAnimeList(): List<Anime>

    @GET("/anime/{animeId}")
    fun getAnimeDetails(
        @Path("animeId") animeId: String
    ): Anime

}