package com.myapplication.presentation.movie_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.myapplication.R

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val bottomBarNav = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(bottomBarNav) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Navigation(navController = bottomBarNav, navController)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, mainNav: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.TopRated.route
    ) {
        composable(
            route = NavigationItem.TopRated.route
        ) {
            MovieListScreen(
                movieListType = MovieListType.TopRatedList,
                navController = mainNav
            )
        }
        composable(
            route = NavigationItem.NowPlaying.route
        ) {
            MovieListScreen(
                movieListType = MovieListType.NowPlayingList,
                navController = mainNav
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.TopRated,
        NavigationItem.NowPlaying,
    )
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selected = item.route == currentRoute,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_star_24),
                        contentDescription = null
                    )
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "List Movies",
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    )
}

sealed class NavigationItem(
    var route: String, /*var icon: Int,*/
    var title: String,
) {
    object TopRated : NavigationItem("topRated", "Top Rated")
    object NowPlaying : NavigationItem("nowPlaying", "Now Playing")
}
