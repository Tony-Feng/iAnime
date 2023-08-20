package com.project.ianime.repository

import com.project.ianime.api.*
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.error.HttpStatus
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
            .onErrorResumeNext {
                if (it is HttpException) {
                    when (it.code()) {
                        HttpStatus.UNAUTHORIZED -> {
                            return@onErrorResumeNext Single.error(
                                UnauthorizedException(it.message())
                            )
                        }
                        HttpStatus.NOT_FOUND -> {
                            return@onErrorResumeNext Single.error(
                                NotFoundException(it.message())
                            )
                        }
                        in 400..599 -> {
                            return@onErrorResumeNext Single.error(
                                ConnectionException(it.message())
                            )
                        }
                    }
                }
                Single.error(it)
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