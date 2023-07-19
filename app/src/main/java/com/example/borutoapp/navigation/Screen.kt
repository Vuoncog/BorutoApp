package com.example.borutoapp.navigation

import com.example.borutoapp.utilities.Constants.DETAIL_ARGUMENT_KEY

sealed class Screen(
    val route: String
) {
    object Splash: Screen(
        route = "splash_screen"
    )
    object Welcome: Screen(
        route = "welcome_screen"
    )
    object Home: Screen(
        route = "home_screen"
    )
    object Detail: Screen(
        route = "detail_screen/{$DETAIL_ARGUMENT_KEY}"
    ){
        fun getArgument(heroId: String): String = "detail_screen/$heroId"
    }
    object Search: Screen(
        route = "search_screen"
    )
}