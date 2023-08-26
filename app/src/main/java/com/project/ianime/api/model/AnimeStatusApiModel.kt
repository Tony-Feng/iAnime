package com.project.ianime.api.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.project.ianime.R

enum class AnimeStatusApiModel(@StringRes val label: Int) {
    @SerializedName("FINISHED")
    FINISHED(R.string.status_finish),

    @SerializedName("INPROGRESS")
    IN_PROGRESS(R.string.status_in_progress);
}
