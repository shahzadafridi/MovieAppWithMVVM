package com.example.TentwentAssignment.data.remote.response.movie.images

data class ImagesResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
)