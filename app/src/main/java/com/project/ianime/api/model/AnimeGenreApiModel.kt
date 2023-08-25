package com.project.ianime.api.model

import com.google.gson.annotations.SerializedName

enum class AnimeGenreApiModel {
    @SerializedName("ADVENTURE")
    ADVENTURE,

    @SerializedName("LOVE")
    LOVE,

    @SerializedName("SCHOOL")
    SCHOOL,

    @SerializedName("ENERGY")
    ENERGY,

    @SerializedName("SCI-FI")
    SCI_FI,

    @SerializedName("MYSTERY")
    MYSTERY,

    @SerializedName("LIFE")
    LIFE,

    @SerializedName("LEGEND")
    LEGEND;
}
