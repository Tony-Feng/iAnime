package com.project.ianime.api.model

import com.google.gson.annotations.SerializedName

/**
 * API response for get anime details
 */
data class AnimeApiModel(
    @SerializedName("anime_id") val animeId: String,
    @SerializedName("anime_name") val animeName: String,
    @SerializedName("rate") val rate: Float,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("type") val type: String,
    @SerializedName("published_year") val publishedYear: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("cover_url") val animeImageUrl: String?
)