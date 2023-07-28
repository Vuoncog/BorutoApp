package com.example.borutoapp.data.repository

import com.example.borutoapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean) =
        dataStoreOperations.saveOnBoardingState(completed = completed)

    fun readOnBoardingState(): Flow<Boolean> = dataStoreOperations.readOnBoardingState()
}