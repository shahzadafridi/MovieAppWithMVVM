package com.example.TentwentAssignment.ui.main

import androidx.lifecycle.*
import com.example.TentwentAssignment.data.local.room.entity.MovieEntity
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

    fun onGetMovies() {

        viewModelScope.launch {
            repository.onMovies()
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

}