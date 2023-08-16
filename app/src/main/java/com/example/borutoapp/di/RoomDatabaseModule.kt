package com.example.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.utilities.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Provides
    @Singleton
    fun providesHeroDao(borutoDatabase: BorutoDatabase) = borutoDatabase.heroDao()

    @Provides
    @Singleton
    fun providesHeroRemoteKeyDao(borutoDatabase: BorutoDatabase) = borutoDatabase.heroRemoteKeyDao()


    @Provides
    @Singleton
    fun providesRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, BorutoDatabase::class.java, BORUTO_DATABASE
    ).allowMainThreadQueries().build()
}