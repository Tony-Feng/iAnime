package com.project.ianime.repository

import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.model.AnimeApiModel
import io.reactivex.rxjava3.core.Single

/**
 * Repository for storing data from network and internal storage
 */
interface AnimeDataRepository {

    /**
     * return entire list of all anime details
     */
    fun getAnimeListFromNetwork() : Single<List<AnimeApiModel>>

    /**
     * return list of anime items on gallery screen
     */
    fun getGalleryList() : Single<List<AnimeGalleryItem>>
}