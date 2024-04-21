package com.project.ianime.repository

import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.model.AnimeApiModel
import io.reactivex.rxjava3.core.Single

/**
 * Repository for managing data from network and database
 */
interface AnimeDataRepository {

    /**
     * return entire list of all anime details from Rest API call
     */
    fun getAnimeListFromNetwork(): Single<List<AnimeApiModel>>


    /**
     * return specific anime details by anime ID
     * @param animeId - id of the target anime
     */
    fun getAnimeDetailsById(animeId: String): AnimeGalleryItem?
}