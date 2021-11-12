package com.myapplication.common

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "0bbe2b10fea353074110f98a0e2c13e3"
    const val PARAM_MOVIE_ID = "movie_id"

    const val CONFIGURATION = "CONFIGURATION"

    // number movies need to add ads
    const val NUMBER_MOVIE_ADD_ADS = 3

    fun calculateMovieListSize(size: Int) = size + (size - 1) / NUMBER_MOVIE_ADD_ADS
}