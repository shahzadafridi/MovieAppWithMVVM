package com.example.TentwentAssignment.ui.main

import androidx.lifecycle.*
import com.example.TentwentAssignment.data.local.room.entity.movie.ImageEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.MovieDetailEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.MovieEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.VideoEntity
import com.example.TentwentAssignment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    val TAG = "MainViewModel"

    val moviesLiveData: MutableLiveData<Resource<MovieEntity>> = MutableLiveData()
    val moviesDetailLiveData: MutableLiveData<Resource<MovieDetailEntity>> = MutableLiveData()
    val videoLiveData: MutableLiveData<Resource<VideoEntity>> = MutableLiveData()
    val imageLiveData: MutableLiveData<Resource<ImageEntity>> = MutableLiveData()

    fun onGetMovies(
        apiKey: String,
        language: String,
        page: String
    ) {

        viewModelScope.launch {
            repository.onMovies(apiKey,language,page)
                .onStart {
                    withContext(Dispatchers.Main) {
                        moviesLiveData.value = Resource(
                            Resource.Status.LOADING,
                            null,
                            "Loading..."
                        )
                    }
                }.catch { error ->
                    withContext(Dispatchers.Main) {
                        moviesLiveData.value = Resource(
                            Resource.Status.ERROR,
                            null,
                            error.message.toString()
                        )
                    }
                }.collect { result ->
                    withContext(Dispatchers.Main) {
                        moviesLiveData.value = Resource(
                            Resource.Status.SUCCESS,
                            result.data,
                            "On movies fetched successfully."
                        )
                    }
                }
        }
    }

    fun onGetMovieDetail(
        apiKey: String,
        movieId: Int
    ) {
        viewModelScope.launch {
            repository.onMovieDetail(apiKey,movieId)
                .onStart {
                    withContext(Dispatchers.Main) {
                        moviesDetailLiveData.value = Resource(
                            Resource.Status.LOADING,
                            null,
                            "Loading..."
                        )
                    }
                }.catch { error ->
                    withContext(Dispatchers.Main) {
                        moviesDetailLiveData.value = Resource(
                            Resource.Status.ERROR,
                            null,
                            error.message.toString()
                        )
                    }
                }.collect { result ->
                    withContext(Dispatchers.Main) {
                        moviesDetailLiveData.value = Resource(
                            Resource.Status.SUCCESS,
                            result.data,
                            "On movies detail fetched successfully."
                        )
                    }
                }
        }
    }

    fun onVideo(
        apiKey: String,
        movieId: Int
    ) {
        viewModelScope.launch {
            repository.onVideo(apiKey,movieId)
                .onStart {
                    withContext(Dispatchers.Main) {
                        videoLiveData.value = Resource(
                            Resource.Status.LOADING,
                            null,
                            "Loading..."
                        )
                    }
                }.catch { error ->
                    withContext(Dispatchers.Main) {
                        videoLiveData.value = Resource(
                            Resource.Status.ERROR,
                            null,
                            error.message.toString()
                        )
                    }
                }.collect { result ->
                    withContext(Dispatchers.Main) {
                        videoLiveData.value = Resource(
                            Resource.Status.SUCCESS,
                            result.data,
                            "On video fetched successfully."
                        )
                    }
                }
        }
    }

    fun onImage(
        apiKey: String,
        movieId: Int
    ) {
        viewModelScope.launch {
            repository.onImage(apiKey,movieId)
                .onStart {
                    withContext(Dispatchers.Main) {
                        imageLiveData.value = Resource(
                            Resource.Status.LOADING,
                            null,
                            "Loading..."
                        )
                    }
                }.catch { error ->
                    withContext(Dispatchers.Main) {
                        imageLiveData.value = Resource(
                            Resource.Status.ERROR,
                            null,
                            error.message.toString()
                        )
                    }
                }.collect { result ->
                    withContext(Dispatchers.Main) {
                        imageLiveData.value = Resource(
                            Resource.Status.SUCCESS,
                            result.data,
                            "On video fetched successfully."
                        )
                    }
                }
        }
    }
}