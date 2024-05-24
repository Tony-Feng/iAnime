package com.project.ianime.di

import com.project.ianime.BuildConfig
import com.project.ianime.api.AcceptLanguageInterceptor
import com.project.ianime.api.AnimeService
import com.project.ianime.repository.AnimeDataRepository
import com.project.ianime.repository.AnimeDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AnimeModule {

    /**
     * Provides accept language interceptor
     */
    @Provides
    @Reusable
    fun providesBasicInterceptor(): AcceptLanguageInterceptor {
        return AcceptLanguageInterceptor()
    }


    /**
     * Provides an okhttp client builder
     */
    @Singleton
    @Provides
    fun providesOkhttpBuilder(acceptLanguageInterceptor: AcceptLanguageInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(acceptLanguageInterceptor)
            .build()
    }

    /**
     * Provides a Retrofit instance
     */
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_PATH)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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

    /**
     * Provides the anime repository
     */
    @Singleton
    @Provides
    fun providesAnimeRepository(impl: AnimeDataRepositoryImpl): AnimeDataRepository {
        return impl
    }
}