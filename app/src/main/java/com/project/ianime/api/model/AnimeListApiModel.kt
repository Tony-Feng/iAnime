package com.project.ianime.api.model

import com.google.gson.annotations.SerializedName

data class AnimeListApiModel(
    @SerializedName("anime_list") val animeList: List<AnimeApiModel>
)
