package com.project.ianime.repository

import androidx.annotation.WorkerThread
import com.project.ianime.api.AnimeService
import com.project.ianime.api.error.*
import com.project.ianime.api.model.AnimeApiModel
import com.project.ianime.data.AnimeDao
import com.project.ianime.data.AnimeEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class AnimeDataRepositoryImpl @Inject constructor(
    private val animeService: AnimeService,
    private val animeDao: AnimeDao
) : AnimeDataRepository {

    // cached the data from the Network resource
    private var cachedAnimeItemsList : List<AnimeApiModel> ?= null

    override fun getAnimeListFromNetwork(): Single<List<AnimeApiModel>> {
        return animeService.getAnimeListFromNetwork()
            .subscribeOn(Schedulers.io())
            .flatMap { response ->
                val animeList = response.animeList ?: emptyList()

                // sort anime list based on rate
                animeList.sortedByDescending {
                    it.rate
                }
                
                cachedAnimeItemsList = animeList
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

    override fun getAnimeDetailsById(animeId: String): AnimeApiModel? {
        return cachedAnimeItemsList?.find {
            it.animeId == animeId
        }
    }

    override fun getOfflineAnimeList() = animeDao.getAllAnimes()

    @WorkerThread
    override suspend fun insertAnimeIntoDatabase(animeEntity: AnimeEntity) = animeDao.insertAnime(animeEntity)

    @WorkerThread
    override suspend fun clearOfflineAnimeList() = animeDao.deleteAll()
}