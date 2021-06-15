package com.example.TentwentAssignment.di

import com.example.TentwentAssignment.MyApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationInstance(): MyApplication {
        return MyApplication()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson{
        return Gson()
    }
}