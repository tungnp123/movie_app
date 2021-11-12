package com.myapplication.presentation

sealed class Screen(val route: String){
    object MapScreen: Screen("map_screen")
    object MainScreen: Screen("main_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}