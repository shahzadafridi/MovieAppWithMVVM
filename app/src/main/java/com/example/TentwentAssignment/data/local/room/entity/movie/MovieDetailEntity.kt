package com.example.TentwentAssignment.data.local.room.entity.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @PrimaryKey()
    @ColumnInfo(name="id")
    val id : Int? = null,
    @ColumnInfo(name="response")
    val response : String
)