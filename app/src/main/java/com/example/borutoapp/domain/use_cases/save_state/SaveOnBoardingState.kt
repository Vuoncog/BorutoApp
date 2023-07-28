package com.example.borutoapp.domain.use_cases.save_state

import com.example.borutoapp.data.repository.Repository

class SaveOnBoardingState(
    private val repository: Repository
) {
    suspend operator fun invoke(
        completed: Boolean
    ) = repository.saveOnBoardingState(completed = completed)
}