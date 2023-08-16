package com.example.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.model.HeroRemoteKey
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    val borutoApi: BorutoApi,
    val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int, Hero>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteDao = borutoDatabase.heroRemoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosetToCurrentPosition(state)
                remoteKey?.nextPage?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKey?.prevPage ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKey != null
                )
                prevPage
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state = state)
                val nextPage = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                nextPage
            }
        }

        return try {
            val response = borutoApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                borutoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteDao.deleteAllRemoteKey()
                    }

                    val listRemoteKeys = response.heroes.map { hero ->
                        HeroRemoteKey(
                            id = hero.id,
                            prevPage = response.prevPage,
                            nextPage = response.nextPage
                        )
                    }
                    heroDao.addHeroes(heroes = response.heroes)
                    heroRemoteDao.addRemoteKeys(heroRemoteKeys = listRemoteKeys)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getRemoteKeyClosetToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let { id ->
                heroRemoteDao.getRemoteKey(
                    heroId = id
                )
            }
        }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull().let { hero ->
            hero?.let {
                heroRemoteDao.getRemoteKey(
                    heroId = it.id
                )
            }
        }
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                heroRemoteDao.getRemoteKey(
                    heroId = it.id
                )
            }
    }
}