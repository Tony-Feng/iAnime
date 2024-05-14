package com.project.ianime.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM ianime_offline_table ORDER BY anime_name ASC")
    fun getAllAnimes(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(animeEntity: AnimeEntity)

    /**
     * suspend function ensures performing heavy-CPU tasks running in background
     */
    @Query("DELETE FROM ianime_offline_table")
    suspend fun deleteAll()
}