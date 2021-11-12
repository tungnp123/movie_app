package com.myapplication.domain.repository.remote

import com.myapplication.common.Resource
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.domain.model.MovieDetail
import com.myapplication.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
     fun getListMovies(path: String, page: Int): Flow<Resource<MovieList>>
     fun getMovieDetail(movie_id: String): Flow<Resource<MovieDetail>>
     fun getConfiguration(): Flow<Resource<ConfigurationDto>>
}