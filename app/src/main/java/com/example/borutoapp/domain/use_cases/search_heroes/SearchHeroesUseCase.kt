package com.example.borutoapp.domain.use_cases.search_heroes

import com.example.borutoapp.data.repository.Repository

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String) = repository.searchHeroes(query = query)
}