package com.example.borutoapp.display.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.Purple200
import com.example.borutoapp.ui.theme.Purple500
import com.example.borutoapp.ui.theme.SuitableBlack

@Composable
fun SplashScreen() {
    val degrees = remember {
        Animatable(
            initialValue = 0f
        )
    }

    LaunchedEffect(key1 = true, block = {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
    })

    Splash(
        degrees = degrees.value
    )
}

@Composable
fun Splash(
    degrees: Float
) {
    val brush: Brush = if (isSystemInDarkTheme()) {
        Brush.verticalGradient(
            listOf(
                SuitableBlack,
                SuitableBlack.copy(alpha = 0.3f)
            )
        )
    } else {
        Brush.verticalGradient(
            listOf(
                Purple500,
                Purple200
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = brush),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.boruto_app_logo),
            contentDescription = stringResource(R.string.app_logo),
            tint = Color.White,
            modifier = Modifier
                .size(216.dp)
                .rotate(degrees = degrees)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDarkMode() {
    SplashScreen()
}