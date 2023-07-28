package com.example.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.display.screens.home.HomeScreen
import com.example.borutoapp.display.screens.splash.SplashScreen
import com.example.borutoapp.display.screens.welcome.WelcomeScreen
import com.example.borutoapp.utilities.Constants.DETAIL_ARGUMENT_KEY

@Composable
fun SetUpNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                navController = navController
            )
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY, builder = {
                type = NavType.IntType
            }))
        ) {

        }
        composable(route = Screen.Search.route) {

        }

    }
}