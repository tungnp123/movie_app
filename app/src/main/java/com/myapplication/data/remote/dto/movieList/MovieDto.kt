package com.myapplication.data.remote.dto.movieList

import com.myapplication.domain.model.Movie

data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        backdrop_path = backdrop_path,
        title = title,
        poster_path = poster_path,
        release_date = release_date.take(4),
        overview = overview,
        vote_average = vote_average.toString(),
    )
}