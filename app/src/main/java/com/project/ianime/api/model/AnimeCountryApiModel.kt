package com.project.ianime.api.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.project.ianime.R

enum class AnimeCountryApiModel(@StringRes val label: Int) {

    @SerializedName("CHINA")
    CHN_ANIME(R.string.country_china),

    @SerializedName("JAPAN")
    JAP_ANIME(R.string.country_japan);
}
