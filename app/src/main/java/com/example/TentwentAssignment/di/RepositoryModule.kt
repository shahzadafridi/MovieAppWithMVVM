package com.example.TentwentAssignment.di

import android.content.Context
import com.example.TentwentAssignment.data.local.room.AppDao
import com.example.TentwentAssignment.data.remote.endpoint.ApiService
import com.example.TentwentAssignment.ui.movie.MovieRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        apiService: ApiService,
        appDao: AppDao,
        gson: Gson,
        @ApplicationContext context: Context
    ): MovieRepository {
        return MovieRepository(apiService,appDao,gson,context)
    }
}