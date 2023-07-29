package com.example.borutoapp.di

import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.data.repository.RemoteDataSourceImpl
import com.example.borutoapp.domain.repository.RemoteDataSource
import com.example.borutoapp.utilities.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.MINUTES)
        .connectTimeout(15, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providesBorutoApi(retrofit: Retrofit): BorutoApi = retrofit.create(BorutoApi::class.java)

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase
    ): RemoteDataSource = RemoteDataSourceImpl(
        borutoApi = borutoApi,
        borutoDatabase = borutoDatabase
    )
}