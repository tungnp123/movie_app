package com.myapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapplication.presentation.map.MapScreen
import com.myapplication.presentation.movie_list.MainScreen
import com.myapplication.presentation.movie_list.MovieDetailScreen


@Composable
fun NavHostController(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(
            route = Screen.MapScreen.route
        ) {
            MapScreen()
        }
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(navController)
        }
        composable(
            route = Screen.MovieDetailScreen.route + "/{movie_id}"
        ) {
            MovieDetailScreen(navController)
        }
    }
}