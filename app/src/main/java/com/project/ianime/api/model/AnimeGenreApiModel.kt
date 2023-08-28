package com.project.ianime.api.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.project.ianime.R

enum class AnimeGenreApiModel(@StringRes val label: Int) {
    @SerializedName("ADVENTURE")
    ADVENTURE(R.string.genre_adventure),

    @SerializedName("LOVE")
    LOVE(R.string.genre_love),

    @SerializedName("SCHOOL")
    SCHOOL(R.string.genre_school),

    @SerializedName("ENERGY")
    ENERGY(R.string.genre_energy),

    @SerializedName("SCI-FI")
    SCI_FI(R.string.genre_sci),

    @SerializedName("MYSTERY")
    MYSTERY(R.string.genre_mystery),

    @SerializedName("LIFE")
    LIFE(R.string.genre_life),

    @SerializedName("LEGEND")
    LEGEND(R.string.genre_legend);
}
