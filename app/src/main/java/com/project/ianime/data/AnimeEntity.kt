package com.project.ianime.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.ianime.api.model.AnimeApiModel
import com.project.ianime.api.model.AnimeCountryApiModel
import com.project.ianime.api.model.AnimeGenreApiModel
import com.project.ianime.api.model.AnimeStatusApiModel

@Entity(tableName = "ianime_offline_table")
class AnimeEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("anime_id") val animeId: String,
    @ColumnInfo("anime_name") val animeName: String,
    @ColumnInfo("rate") val rate: Float,
    @ColumnInfo("status") val status: AnimeStatusApiModel,
    @ColumnInfo("country") val country: AnimeCountryApiModel,
    @ColumnInfo("genre") val type: AnimeGenreApiModel,
    @ColumnInfo("release_year") val releaseYear: String?,
    @ColumnInfo("synopsis") val synopsis: String?,
    @ColumnInfo("cover_url") val animeImageUrl: String?
){
    fun mapToAnimeItem():AnimeApiModel{
        return AnimeApiModel(
            animeId,
            animeName,
            rate,
            status,
            country,
            type,
            releaseYear,
            synopsis,
            animeImageUrl
        )
    }
}