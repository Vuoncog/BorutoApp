package com.example.borutoapp.di

import android.content.Context
import com.example.borutoapp.data.repository.DataStoreOperationsImpl
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.repository.DataStoreOperations
import com.example.borutoapp.domain.use_cases.UseCases
import com.example.borutoapp.domain.use_cases.read_state.ReadOnBoardingState
import com.example.borutoapp.domain.use_cases.save_state.SaveOnBoardingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreOperationsModule {

    @Provides
    @Singleton
    fun providesDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun providesUseCases(
        repository: Repository
    ): UseCases =
        UseCases(
            readOnBoardingState = ReadOnBoardingState(repository),
            saveOnBoardingState = SaveOnBoardingState(repository)
        )
}