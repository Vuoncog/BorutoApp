package com.example.borutoapp.display.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow(false)
    val onBoardingState: StateFlow<Boolean> = _onBoardingState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingState.value =
                useCases.readOnBoardingState().stateIn(viewModelScope).value
        }
    }
}