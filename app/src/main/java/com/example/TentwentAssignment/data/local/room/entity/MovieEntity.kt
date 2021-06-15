package com.example.TentwentAssignment.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id : Int? = null,
    @ColumnInfo(name="response")
    val response : String
)