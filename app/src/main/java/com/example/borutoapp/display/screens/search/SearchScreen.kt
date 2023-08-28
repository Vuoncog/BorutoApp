package com.example.borutoapp.display.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery = searchViewModel.searchQuery.value

    Scaffold(
        topBar = {
            SearchAppBar(
                text = searchQuery,
                onTextChanged = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {},
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {

    }
}