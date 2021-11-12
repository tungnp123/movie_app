package com.myapplication.data.remote.dto.movieDetail

import com.myapplication.domain.model.MovieDetail

data class MovieDetailDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailDto.toMovieDetail() = MovieDetail(
    id = id,
    backdrop_path = backdrop_path,
    title = title,
    release_date = release_date.take(4),
    vote_average = vote_average,
    runtime = runtime,
    overview = overview,
    genres = genres.map { it.name },
)
