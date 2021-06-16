package com.example.TentwentAssignment.data.local.room.entity.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey()
    @ColumnInfo(name="id")
    val id : Int? = null,
    @ColumnInfo(name="response")
    val response : String
)