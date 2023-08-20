package com.project.ianime.di

import com.project.ianime.screens.gallery.GalleryFragment
import com.project.ianime.screens.manageanime.ManageAnimeFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Application module used to provide dependencies for given target classes
 */
@Singleton
@Component(
    modules = [AnimeModule::class]
)
interface ApplicationComponent {

    fun inject(galleryFragment: GalleryFragment)

    fun inject(manageAnimeFragment: ManageAnimeFragment)
}