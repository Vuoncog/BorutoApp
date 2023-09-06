package com.example.borutoapp.display.screens.detail

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.use_cases.UseCases
import com.example.borutoapp.utilities.Constants.DETAIL_ARGUMENT_KEY
import com.example.borutoapp.utilities.PaletteGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _selectedHero : MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAIL_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let { useCases.getSelectedHeroUseCases(it) }
        }
    }

    private val _uiEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    fun generatePaletteEvent(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GeneratePalette)
        }
    }

    private val _colorPalette: MutableStateFlow<Map<String,String>> = MutableStateFlow(mapOf())
    val colorPalette: StateFlow<Map<String, String>> = _colorPalette

    fun generateColorPalette(bitmap: Bitmap){
        _colorPalette.value = PaletteGenerator.extractColorFromBitmap(
            bitmap = bitmap
        )
    }


}

sealed class UiEvent(){
    object GeneratePalette: UiEvent()
}