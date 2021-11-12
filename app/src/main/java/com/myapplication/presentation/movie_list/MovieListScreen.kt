package com.myapplication.presentation.movie_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.myapplication.common.Constants
import com.myapplication.presentation.Screen
import com.myapplication.presentation.movie_list.components.AdvertiseItem
import com.myapplication.presentation.movie_list.components.MovieListItem

@Composable
fun MovieListScreen(
    movieListType: MovieListType,
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    viewModel.path = movieListType.type
    val state = viewModel.movies.value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.resetListState()
                viewModel.getListMovies()
            },
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(Constants.calculateMovieListSize(state.size)) { index ->
                    viewModel.onChangeListPosition(index)
                    if ((index + 1) >= Constants.calculateMovieListSize(viewModel.page.value * viewModel.page_size) && !viewModel.isLoading.value) {
                        viewModel.nextPage()
                    }
                    if ((index + 1) % (Constants.NUMBER_MOVIE_ADD_ADS + 1) == 0)
                        AdvertiseItem()
                    else
                        MovieListItem(
                            configurationDto = viewModel.configure.value,
                            movie = state[index - index /(Constants.NUMBER_MOVIE_ADD_ADS + 1)],
                            onItemClick = {
                                navController.navigate(Screen.MovieDetailScreen.route + "/${state[index].id}")
                            })
                }
            }
            if (viewModel.error.value.isNotBlank()) {
                Text(
                    text = viewModel.error.value,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            } else
                if (viewModel.isLoading.value && state.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
        }
    }
}


enum class MovieListType(val type: String) {
    TopRatedList("top_rated"),
    NowPlayingList("now_playing")
}