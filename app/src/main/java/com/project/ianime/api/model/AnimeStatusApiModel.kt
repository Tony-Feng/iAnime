package com.project.ianime.api.model

import com.google.gson.annotations.SerializedName

enum class AnimeStatusApiModel {
    @SerializedName("FINISHED")
    FINISHED,
    @SerializedName("INPROGRESS")
    IN_PROGRESS;
}
