package com.example.borutoapp.domain.use_cases

import com.example.borutoapp.domain.use_cases.get_heroes.GetAllHeroesUseCases
import com.example.borutoapp.domain.use_cases.read_state.ReadOnBoardingState
import com.example.borutoapp.domain.use_cases.save_state.SaveOnBoardingState

data class UseCases(
    val readOnBoardingState: ReadOnBoardingState,
    val saveOnBoardingState: SaveOnBoardingState,
    val getAllHeroesUseCases: GetAllHeroesUseCases
)