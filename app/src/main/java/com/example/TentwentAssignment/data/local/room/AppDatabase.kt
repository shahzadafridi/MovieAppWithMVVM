package com.example.TentwentAssignment.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.TentwentAssignment.data.local.room.entity.*

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun appDao(): AppDao

}