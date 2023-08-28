package com.project.ianime.api.data

/**
 * Data model which includes anime information on Gallery Screen
 */
data class AnimeGalleryItem(
    val animeId: String,
    val imageUrl: String?,
    val animeRate: Float,
    val animeName: String,
    val animeSynopsis: String?
)
