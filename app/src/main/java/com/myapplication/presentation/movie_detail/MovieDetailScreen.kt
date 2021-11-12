package com.myapplication.presentation.movie_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.myapplication.R
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.presentation.movie_detail.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val movie = state.movie
    Scaffold(
        topBar = { DetailTopBar(movie?.title ?: "", navController) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Column {
                    if (movie != null) {
                        val configure: ConfigurationDto?  = viewModel.configure.value
                        if(configure != null) {
                            val painter =
                                rememberImagePainter(data = "${configure.images?.secure_base_url ?: ""}${configure.images?.backdrop_sizes?.last() ?: ""}${movie.backdrop_path}",
                                    builder = {
                                        placeholder(R.drawable.placeholder)
                                    })
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth().size(240.dp)
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.Black)
                        )
                        Text(
                            text = "${movie.title} (${movie.release_date})",
                            style = MaterialTheme.typography.h4
                        )
                        Row {
                            Image(
                                modifier = Modifier
                                    .size(12.dp)
                                    .align(Alignment.CenterVertically),
                                painter = painterResource(id = R.drawable.ic_baseline_star_24),
                                contentDescription = null,
                            )
                            var genres = ""
                            movie.genres.forEach { genres += "$it " }
                            Text(
                                text = "${movie.vote_average} | ${movie.runtime / 60}h :${movie.runtime % 60}m | $genres",
                                style = MaterialTheme.typography.h6,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.Black)
                        )
                        Text(
                            text = movie.overview,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }

                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    )
}

@Composable
fun DetailTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = null
                )
            }
        },
        actions = {
            Box(modifier = Modifier.size(width = 70.dp, height = 50.dp)) {}
        }
    )
}