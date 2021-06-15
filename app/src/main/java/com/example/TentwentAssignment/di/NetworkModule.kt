package com.example.TentwentAssignment.di

import com.example.TentwentAssignment.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
    }

}