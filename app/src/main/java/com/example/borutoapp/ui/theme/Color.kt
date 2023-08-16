package com.example.borutoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val SuitableBlack = Color(0xFF1B1B1A)
val SuitableWhite = Color(0xFFFAFAFA)

val DarkGray = Color(0xFF3A3A3A)
val LightGray = Color(0xFFD3D3D3)

val DarkShimmerGray = Color(0xFF222222)
val LightShimmerGray = Color(0xFFEEEEEE)

val Colors.backgroundTheme
    @Composable
    get() = if(isLight) SuitableWhite else SuitableBlack

val Colors.fontTheme
    @Composable
    get() = if(isLight) DarkGray else LightGray

val Colors.activeIndicatorTheme
    @Composable
    get() = if (isLight) Purple500 else Purple200

val Colors.appBarTheme
    @Composable
    get() = if (isLight) Purple500 else SuitableBlack

val Colors.colorOnPrimary
    @Composable
    get() = if (isLight) SuitableWhite else LightGray

val Colors.itemBackgroundOnPrimary
    @Composable
    get() = if (isLight) LightGray else DarkGray

val Colors.shimmerItemBackground
    @Composable
    get() = if (isLight) LightShimmerGray else DarkShimmerGray
