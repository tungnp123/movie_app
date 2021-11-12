package com.myapplication.presentation.movie_detail

import com.myapplication.domain.model.Movie
import com.myapplication.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)
