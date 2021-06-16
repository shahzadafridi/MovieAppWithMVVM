package com.example.TentwentAssignment.data.remote.response.movie.video

data class Result(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)