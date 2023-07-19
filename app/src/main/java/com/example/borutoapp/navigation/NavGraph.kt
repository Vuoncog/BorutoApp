package com.example.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.display.screens.splash.SplashScreen
import com.example.borutoapp.display.screens.welcome.WelcomeScreen
import com.example.borutoapp.utilities.Constants.DETAIL_ARGUMENT_KEY

@Composable
fun SetUpNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen()
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen()
        }
        composable(route = Screen.Home.route) {

        }
        composable(route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY, builder = {
                type = NavType.IntType
            }))
        ) {

        }
        composable(route = Screen.Search.route) {

        }

    }
}