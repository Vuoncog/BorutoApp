package com.example.borutoapp.domain.use_cases.read_state

import com.example.borutoapp.data.repository.Repository

class ReadOnBoardingState(
    private val repository: Repository
) {
    operator fun invoke() = repository.readOnBoardingState()
}