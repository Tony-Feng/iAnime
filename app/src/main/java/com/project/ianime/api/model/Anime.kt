package com.project.ianime.api.model

import java.io.Serializable

data class Anime(
    val animeChineseName: String?,
    val animeEnglishName: String?,
    val animeRate: String?,
    val animeDescription: String?,
    val animeCountry: String?,
    val animeType: String?,
    val animeImage: String?,
    val animePublishedYear: String?,
    val animeState: String?,
): Serializable {

    constructor(response: AnimeDataElement?): this(
        response?.animeChName ?: "",
        response?.animeENName ?: "",
        response?.rate ?: "",
        response?.description ?: "",
        response?.country ?: "",
        response?.type ?: "",
        response?.image ?: "",
        response?.publishedYear ?: "",
        response?.state ?: ""
    )


}
