package com.project.ianime.di

import android.app.Application

class AnimeApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}