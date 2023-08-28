package com.example.borutoapp.display.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.borutoapp.display.common.ErrorScreen
import com.example.borutoapp.display.common.HeroItem
import com.example.borutoapp.display.components.ShimmerEffect
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.SMALL_PADDING

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Log.d("ListHero", allHeroes.loadState.toString())

    Scaffold(
        topBar = {
            HomeAppBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        content = {
            HomeContent(
                allHeroes = allHeroes
            )
        }
    )
}

@Composable
fun HomeContent(
    allHeroes: LazyPagingItems<Hero>
) {
    val loadingResult = handlePagingResult(heroes = allHeroes)

    if (loadingResult) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            modifier = Modifier.padding(MEDIUM_PADDING)
        ) {
            items(
                count = allHeroes.itemCount,
                key = allHeroes.itemKey { hero -> hero.id }
            ) { index ->
                val item = allHeroes[index]
                item?.let { hero -> HeroItem(hero = hero) }
            }

        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                ErrorScreen(error = error)
                false
            }
            else -> true
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}