package com.example.borutoapp.display.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.borutoapp.display.components.ShimmerEffect
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.SMALL_PADDING

@Composable
fun ListHeroes(
    allHeroes: LazyPagingItems<Hero>,
    navController: NavHostController,
    isSearchScreen: Boolean = false
) {
    val loadingResult = handlePagingResult(
        heroes = allHeroes,
        isSearchScreen = isSearchScreen
    )

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
                item?.let { hero ->
                    HeroItem(
                        hero = hero,
                        navController = navController
                    )
                }
            }

        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>,
    isSearchScreen: Boolean
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                StateScreen(error = error, heroes = heroes)
                false
            }
            heroes.itemCount < 1 && isSearchScreen -> {
                StateScreen(heroes = heroes)
                false
            }
            else -> true
        }
    }
}