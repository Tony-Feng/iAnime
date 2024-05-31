package com.project.ianime.repository

import com.project.ianime.api.model.AnimeApiModel
import com.project.ianime.data.AnimeEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

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
    fun getAnimeDetailsById(animeId: String): AnimeApiModel?

    /**
     * returns entire list of all anime details from the offline database
     */
    fun getOfflineAnimeList(): Flow<List<AnimeEntity>>

    /**
     * add each anime into locale database
     */
    suspend fun insertAnimeIntoDatabase(animeEntity: AnimeEntity)

    /**
     * clear all data from the local database
     */
    suspend fun clearOfflineAnimeList()


    /**
     * check if the locale database is empty or not
     */
    fun isDatabaseEmpty(): Boolean
}