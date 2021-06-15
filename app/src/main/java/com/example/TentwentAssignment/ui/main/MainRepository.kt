package com.example.TentwentAssignment.ui.main

import android.content.Context
import android.util.Log
import com.example.TentwentAssignment.data.local.room.AppDao
import com.example.TentwentAssignment.data.local.room.entity.MovieEntity
import com.example.TentwentAssignment.data.remote.endpoint.ApiService
import com.example.TentwentAssignment.data.remote.response.MovieResponse
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

    suspend fun onMoviesList() = flow<MovieResponse> {
        flowOf(apiService.onMoviesList())
            .catch {
                throw Exception(it.message)
            }.map {
                emit(it)
            }.collect()
    }.flowOn(Dispatchers.IO)

    fun onMovies(): Flow<Resource<MovieEntity>> {
        return networkBoundResource(
            query = { appDao.queryMovieResponse() },
            fetch = { apiService.onMoviesList() },
            saveFetchResult = { items -> appDao.insertMovieResponse(MovieEntity(null,gson.toJson(items))) },
            onFetchFailed = { throwable -> Log.e("ERROR",throwable.message.toString()) },
            shouldFetch = { (Utility.checkConnection(context)) }
        )
    }
}