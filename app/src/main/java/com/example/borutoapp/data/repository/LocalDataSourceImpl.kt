package com.example.borutoapp.data.repository

import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val borutoDatabase: BorutoDatabase
): LocalDataSource {
    val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}