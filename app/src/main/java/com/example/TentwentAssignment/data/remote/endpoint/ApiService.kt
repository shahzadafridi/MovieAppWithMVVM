package com.example.TentwentAssignment.data.remote.endpoint

import com.example.TentwentAssignment.data.remote.response.MovieResponse
import retrofit2.http.*

/**
 * REST API access points
 */

interface ApiService {

    @GET("3/movie/top_rated?api_key=f8ce66414380d5680627c776432bafaa&language=en-US&page=1")
    suspend fun onMoviesList(): MovieResponse

}