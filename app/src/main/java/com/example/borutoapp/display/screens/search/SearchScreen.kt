package com.example.borutoapp.display.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.display.common.HeroItem
import com.example.borutoapp.display.common.ListHeroes

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery = searchViewModel.searchQuery.value
    val searchedHero = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchAppBar(
                text = searchQuery,
                onTextChanged = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = searchQuery)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListHeroes(
                allHeroes = searchedHero,
                isSearchScreen = true,
                navController = navController
            )
        }
    )
}