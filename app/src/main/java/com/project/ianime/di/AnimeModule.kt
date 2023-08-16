package com.project.ianime.di

import com.project.ianime.api.AcceptLanguageInterceptor
import com.project.ianime.api.AnimeService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
class AnimeModule {

    /**
     * Provides an okhttp client builder
     */
    @Provides
    fun providesOkhttpBuilder(acceptLanguageInterceptor: AcceptLanguageInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(acceptLanguageInterceptor)
            .build()
    }

    /**
     * Provides a Retrofit instance
     */
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            // TODO 08-16: Add in properties build file
            .baseUrl("https://ai8454431.pythonanywhere.com/")
            .client(okHttpClient)
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