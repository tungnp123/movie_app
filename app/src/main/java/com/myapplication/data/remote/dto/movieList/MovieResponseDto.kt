package com.myapplication.data.remote.dto.movieList

import com.myapplication.domain.model.MovieList

data class MovieResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)

fun MovieResponseDto.toMovieList() = MovieList(
    page = page,
    results = results.map { it.toMovie() },
    total_pages = total_pages,
    total_results = total_results
)