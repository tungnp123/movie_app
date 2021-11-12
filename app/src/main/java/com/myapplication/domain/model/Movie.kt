package com.myapplication.domain.model

data class Movie(
    val backdrop_path: String,
    val poster_path: String,
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val vote_average: String,
)