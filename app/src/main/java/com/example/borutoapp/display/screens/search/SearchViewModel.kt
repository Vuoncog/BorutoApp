package com.example.borutoapp.display.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class SearchViewModel: ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchQuery(query: String){
        _searchQuery.value = query
    }
}