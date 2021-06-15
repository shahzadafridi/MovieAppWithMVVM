package com.example.TentwentAssignment.data.local.room

import androidx.room.*
import com.example.TentwentAssignment.data.local.room.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieResponse(movie: MovieEntity) : Long

    @Query("SELECT * FROM movie")
    fun queryMovieResponse(): Flow<MovieEntity>
}