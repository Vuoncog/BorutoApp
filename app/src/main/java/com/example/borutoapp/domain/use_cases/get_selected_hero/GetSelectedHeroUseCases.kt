package com.example.borutoapp.domain.use_cases.get_selected_hero

import com.example.borutoapp.data.repository.Repository

class GetSelectedHeroUseCases(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int) = repository.getSelectedHero(heroId)
}