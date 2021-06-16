package com.example.TentwentAssignment.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.TentwentAssignment.data.local.room.entity.movie.ImageEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.MovieDetailEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.MovieEntity
import com.example.TentwentAssignment.data.local.room.entity.movie.VideoEntity

@Database(entities = [MovieEntity::class, MovieDetailEntity::class, VideoEntity::class, ImageEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun appDao(): AppDao

}