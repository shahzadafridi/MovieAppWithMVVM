package com.example.TentwentAssignment.ui.main

import android.content.Context
import android.util.Log
import com.example.TentwentAssignment.data.local.room.AppDao
import com.example.TentwentAssignment.data.local.room.entity.movie.ImageEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.MovieDetailEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.MovieEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.VideoEntity
import com.example.TentwentAssignment.data.remote.endpoint.ApiService
import com.example.TentwentAssignment.data.remote.response.movie.MovieResponse
import com.example.TentwentAssignment.util.Resource
import com.example.TentwentAssignment.util.Utility
import com.example.TentwentAssignment.util.Utility.networkBoundResource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MainRepository(
        private val apiService: ApiService,
        private val appDao: AppDao,
        private val gson: Gson,
        private val context: Context
) {

    suspend fun onMoviesList(
        apiKey: String,
        language: String,
        page: String
    ) = flow<MovieResponse> {
        flowOf(apiService.onMoviesList(apiKey,language,page))
            .catch {
                throw Exception(it.message)
            }.map {
                emit(it)
            }.collect()
    }.flowOn(Dispatchers.IO)

    fun onMovies(
        apiKey: String,
        language: String,
        page: String
    ): Flow<Resource<MovieEntity>> {
        return networkBoundResource(
            query = { appDao.queryMovieResponse(page.toInt()) },
            fetch = { apiService.onMoviesList(apiKey,language,page) },
            saveFetchResult = { items -> appDao.insertMovieResponse(MovieEntity(page.toInt(),gson.toJson(items))) },
            onFetchFailed = { throwable -> Log.e("ERROR",throwable.message.toString()) },
            shouldFetch = { (Utility.checkConnection(context)) }
        )
    }

    fun onMovieDetail(
        apiKey: String,
        movieId: Int
    ): Flow<Resource<MovieDetailEntity>> {
        return networkBoundResource(
            query = { appDao.queryMovieDetailResponse(movieId) },
            fetch = { apiService.onMovieDetail(movieId,apiKey) },
            saveFetchResult = { items -> appDao.insertMovieDetailResponse(MovieDetailEntity(movieId,gson.toJson(items))) },
            onFetchFailed = { throwable -> Log.e("ERROR",throwable.message.toString()) },
            shouldFetch = { (Utility.checkConnection(context)) }
        )
    }

    fun onVideo(
        apiKey: String,
        movieId: Int
    ): Flow<Resource<VideoEntity>> {
        return networkBoundResource(
            query = { appDao.queryVideoResponse(movieId) },
            fetch = { apiService.onVideo(movieId,apiKey) },
            saveFetchResult = { items -> appDao.insertVideoResponse(VideoEntity(movieId,gson.toJson(items))) },
            onFetchFailed = { throwable -> Log.e("ERROR",throwable.message.toString()) },
            shouldFetch = { (Utility.checkConnection(context)) }
        )
    }

    fun onImage(
        apiKey: String,
        movieId: Int
    ): Flow<Resource<ImageEntity>> {
        return networkBoundResource(
            query = { appDao.queryImageResponse(movieId) },
            fetch = { apiService.onImages(movieId,apiKey) },
            saveFetchResult = { items -> appDao.insertImageResponse(ImageEntity(movieId,gson.toJson(items))) },
            onFetchFailed = { throwable -> Log.e("ERROR",throwable.message.toString()) },
            shouldFetch = { (Utility.checkConnection(context)) }
        )
    }
}