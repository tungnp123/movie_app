package com.myapplication.presentation.movie_list

import com.myapplication.domain.model.Movie
import com.myapplication.domain.model.MovieList

data class MovieListState(
    var isLoading: Boolean = false,
    var data: List<Movie> = emptyList(),
    var error: String = ""
)
