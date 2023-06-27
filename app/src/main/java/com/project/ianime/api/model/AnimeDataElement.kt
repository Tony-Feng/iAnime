package com.project.ianime.api.model

import com.google.gson.annotations.SerializedName

class AnimeDataElement(
    @SerializedName("name_zh") val animeChName: String? = "",
    @SerializedName("name_en") val animeENName: String? = "",
    @SerializedName("rate") val rate: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("image") val image: String? = "",
    @SerializedName("country") val country: String? = "",
    @SerializedName("type") val type: String? = "",
    @SerializedName("published_year") val publishedYear: String? = "",
    @SerializedName("state") val state: String? = ""
)