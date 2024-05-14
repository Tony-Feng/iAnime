package com.project.ianime.di

import android.app.Application
import com.project.ianime.data.AnimeDatabase

class AnimeApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    val animeDatabase by lazy { AnimeDatabase.getDatabase(this) }
    val animeDao by lazy { animeDatabase.animeDao() }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .animeModule(AnimeModule(this))
            .build()
    }
}