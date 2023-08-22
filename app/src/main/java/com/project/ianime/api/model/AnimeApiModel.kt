package com.project.ianime.api.model

import com.google.gson.annotations.SerializedName
import com.project.ianime.api.data.AnimeGalleryItem

/**
 * API response for get single anime details
 */
data class AnimeApiModel(
    @SerializedName("anime_id") val animeId: String,
    @SerializedName("anime_name") val animeName: String,
    @SerializedName("rate") val rate: Float,
    @SerializedName("status") val status: AnimeStatusApiModel,
    @SerializedName("country") val country: AnimeCountryApiModel,
    @SerializedName("genre") val type: AnimeGenreApiModel,
    @SerializedName("release_year") val releaseYear: String?,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("cover_url") val animeImageUrl: String?
) {
    fun mapToGalleryItem(): AnimeGalleryItem {
        return AnimeGalleryItem(
            animeId = animeId,
            imageUrl = animeImageUrl,
            animeRate = rate,
            animeName = animeName,
            animeSynopsis = synopsis
        )
    }
}