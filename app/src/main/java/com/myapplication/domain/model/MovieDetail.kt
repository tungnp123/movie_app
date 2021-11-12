package com.myapplication.domain.model

data class MovieDetail(
    val backdrop_path: String,
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val vote_average: Double,
    val runtime: Int,
    val genres: List<String>,
)