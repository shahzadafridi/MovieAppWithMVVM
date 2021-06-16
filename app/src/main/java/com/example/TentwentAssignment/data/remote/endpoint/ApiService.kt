package com.example.TentwentAssignment.data.remote.endpoint

import com.example.TentwentAssignment.data.remote.response.movie.MovieResponse
import com.example.TentwentAssignment.data.remote.response.movie.detail.MovieDetailResponse
import com.example.TentwentAssignment.data.remote.response.movie.images.ImagesResponse
import com.example.TentwentAssignment.data.remote.response.movie.video.VideoResponse
import retrofit2.http.*

/**
 * REST API access points
 */

interface ApiService {

    @GET("3/movie/top_rated")
    suspend fun onMoviesList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): MovieResponse

    @GET("3/movie/{id}")
    suspend fun onMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

    @GET("3/movie/{id}/videos")
    suspend fun onVideo(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): VideoResponse

    @GET("3/movie/{id}/images")
    suspend fun onImages(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): ImagesResponse

}