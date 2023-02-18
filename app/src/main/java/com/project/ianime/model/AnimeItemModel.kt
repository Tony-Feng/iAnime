package com.project.ianime.model

import android.graphics.drawable.Drawable

/**
 * Model class used to hold each anime item information
 */
data class AnimeItemModel(
    // TODO 2023-02-11: Update imageIcon type
    val imageIcon: Int,
    val animeName: String,
    val animeDescription: String
) {
}