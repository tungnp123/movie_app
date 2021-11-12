package com.myapplication.data

import com.myapplication.common.Constants
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.data.remote.dto.movieDetail.MovieDetailDto
import com.myapplication.data.remote.dto.movieList.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{path}")
    suspend fun getListMovies(
        @Path("path") path: String,
        @Query("page") page: Int = 1,
        @Query("api_key") api_key: String = Constants.API_KEY,
    ): MovieResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String = Constants.API_KEY
    ): MovieDetailDto

    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") api_key: String = Constants.API_KEY
    ): ConfigurationDto
}