package com.project.ianime.api.model

import com.google.gson.annotations.SerializedName

/**
 * API response of send new anime details (exclude image from a unique API)
 */
data class AnimeRequestApiModel(
    @SerializedName("anime_en") val animeEnglishName: String,
    @SerializedName("anime_zh") val animeChineseName: String,
    @SerializedName("rate") val animeRate: Float,
    @SerializedName("state") val animeState: String,
    @SerializedName("country") val animeCountry: String,
    @SerializedName("type") val animeType: String,
    @SerializedName("published_year") val animePublishedYear: String?,
    @SerializedName("description") val animeDescription: String?
)
