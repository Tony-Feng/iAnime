package com.project.ianime.di

import com.project.ianime.api.AnimeService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AnimeModule {

    /**
     * Provides a Retrofit instance
     */
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
                // TODO: Add in properties build file
            .baseUrl("https://ai8454431.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides the anime service
     */
    @Singleton
    @Provides
    fun providesAnimeService(retrofit: Retrofit): AnimeService {
        return retrofit.create(AnimeService::class.java)
    }
}