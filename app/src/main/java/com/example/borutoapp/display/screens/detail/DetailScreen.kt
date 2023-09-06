package com.example.borutoapp.display.screens.detail

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.borutoapp.utilities.Constants.BASE_URL
import com.example.borutoapp.utilities.PaletteGenerator
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    navController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val selectedHero = detailViewModel.selectedHero.collectAsState()
    val colorPalette = detailViewModel.colorPalette.collectAsState()

    if (colorPalette.value.isNotEmpty()) {
        Log.d("ColorPalette", colorPalette.value.toString())
        val vibrant = colorPalette.value["vibrant"]
        val darkVibrant = colorPalette.value["darkVibrant"]
        val onDarkVibrant = colorPalette.value["onDarkVibrant"]
        val lightVibrant = colorPalette.value["lightVibrant"]
        selectedHero.value?.let {
            DetailContent(
                hero = it,
                textColor = Color(parseColor(onDarkVibrant)),
                iconColor = Color(parseColor(lightVibrant)),
                backgroundColor = Color(parseColor(darkVibrant)),
                onCloseClicked = { navController.popBackStack() }
            )
        }
    } else {
        detailViewModel.generatePaletteEvent()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailViewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.GeneratePalette -> {
                    val bitmap = PaletteGenerator.convertUrlToBitmap(
                        imageUrl = "${BASE_URL}${selectedHero.value?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailViewModel.generateColorPalette(bitmap = bitmap)
                    }
                }
            }
        }
    }
}
