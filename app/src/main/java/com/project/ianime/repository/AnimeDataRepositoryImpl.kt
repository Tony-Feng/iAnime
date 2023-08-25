package com.project.ianime.repository

import com.project.ianime.api.AnimeService
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.error.*
import com.project.ianime.api.model.AnimeApiModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class AnimeDataRepositoryImpl @Inject constructor(private val animeService: AnimeService) :
    AnimeDataRepository {

    override fun getAnimeListFromNetwork(): Single<List<AnimeApiModel>> {
        return animeService.getAnimeList()
            .subscribeOn(Schedulers.io())
            .map { it.animeList }
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

    override fun getGalleryList(): Single<List<AnimeGalleryItem>> {
        return getAnimeListFromNetwork()
            .map { animeList ->
                animeList.map {
                    it.mapToGalleryItem()
                }
            }
            .flatMap { galleryList ->
                Single.just(galleryList)
            }
    }
}