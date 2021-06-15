package com.akshay.newsapp.news.di

import com.example.TentwentAssignment.data.remote.endpoint.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}