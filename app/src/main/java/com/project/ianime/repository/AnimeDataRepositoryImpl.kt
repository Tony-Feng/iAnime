package com.project.ianime.repository

import com.project.ianime.api.AnimeService
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.error.*
import com.project.ianime.api.model.AnimeApiModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class AnimeDataRepositoryImpl @Inject constructor(private val animeService: AnimeService) : AnimeDataRepository {

    // cached the data from the Network resource
    private var cachedAnimeDetailsList : List<AnimeGalleryItem> ?= null

    override fun getAnimeListFromNetwork(): Single<List<AnimeApiModel>> {
        return animeService.getAnimeList()
            .subscribeOn(Schedulers.io())
            .flatMap { response ->
                val animeList = response.animeList ?: emptyList()
                val animeDetails = animeList.map {
                    it.mapToGalleryItem()
                }
                cachedAnimeDetailsList = animeDetails
                Single.just(animeList)
            }
            .onErrorResumeNext { exception ->
                if (exception is HttpException) {
                    exception.code().let {
                        when {
                            it == HttpStatus.BAD_REQUEST -> {
                                return@onErrorResumeNext Single.error(
                                    BadRequestException(exception.message())
                                )
                            }

                            it == HttpStatus.UNAUTHORIZED -> {
                                return@onErrorResumeNext Single.error(
                                    UnauthorizedException(exception.message())
                                )
                            }

                            it == HttpStatus.NOT_FOUND -> {
                                return@onErrorResumeNext Single.error(
                                    NotFoundException(exception.message())
                                )
                            }

                            it >= 500 -> {
                                return@onErrorResumeNext Single.error(
                                    ConnectionException(exception.message())
                                )
                            }

                            else -> {
                                return@onErrorResumeNext Single.error(exception)
                            }
                        }
                    }
                }
                Single.error(exception)
            }
    }

    override fun getAnimeDetailsById(animeId: String): AnimeGalleryItem? {
        return cachedAnimeDetailsList?.find {
            it.animeId == animeId
        }
    }
}