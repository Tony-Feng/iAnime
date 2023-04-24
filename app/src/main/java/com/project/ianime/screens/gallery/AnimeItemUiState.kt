package com.project.ianime.screens.gallery

/**
 * Model class used to hold each anime item information
 */
data class AnimeItemUiState(
    // TODO 2023-02-11: Update imageIcon type
    val imageIcon: Int,
    val animeRate: Double,
    val animeName: String,
    val animeDescription: String
) {
}