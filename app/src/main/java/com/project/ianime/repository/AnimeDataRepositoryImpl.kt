package com.project.ianime.repository

import com.project.ianime.api.AnimeService
import com.project.ianime.api.EntitlementException
import com.project.ianime.api.HttpStatus
import com.project.ianime.api.NotFoundException
import com.project.ianime.api.data.AnimeGalleryItem
import com.project.ianime.api.model.AnimeApiModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class AnimeDataRepositoryImpl @Inject constructor(private val animeService: AnimeService): AnimeDataRepository {

    override fun getAnimeListFromNetwork(): Single<List<AnimeApiModel>> {
        return animeService.getAnimeList()
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext {
                if (it is HttpException){
                    when (it.code()){
                        HttpStatus.FORBIDDEN -> {
                            return@onErrorResumeNext Single.error(
                                EntitlementException(it.message())
                            )
                        }
                        HttpStatus.NOT_FOUND -> {
                            return@onErrorResumeNext Single.error(
                                NotFoundException(it.message())
                            )
                        }
                    }
                }
                Single.error(it)
            }
    }

    override fun getGalleryList(): Single<List<AnimeGalleryItem>>{
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