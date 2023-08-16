package com.example.borutoapp.domain.use_cases.get_heroes

import com.example.borutoapp.data.repository.Repository

class GetAllHeroesUseCases(
    private val repository: Repository
) {
    operator fun invoke() = repository.getAllHeroes()
}