package com.myapplication.data.repository.remote

import com.myapplication.common.Resource
import com.myapplication.data.MovieApi
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.data.remote.dto.movieDetail.toMovieDetail
import com.myapplication.data.remote.dto.movieList.toMovieList
import com.myapplication.domain.model.MovieDetail
import com.myapplication.domain.model.MovieList
import com.myapplication.domain.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override fun getListMovies(path: String, page: Int): Flow<Resource<MovieList>> = flow {
        try {
            emit(Resource.Loading<MovieList>())
            val response = api.getListMovies(path = path, page)
            emit(Resource.Success<MovieList>(response.toMovieList()))
        } catch (e: HttpException) {
            emit(
                Resource.Error<MovieList>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<MovieList>("Couldn't reach server, check your internet"))
        }
    }

    override fun getMovieDetail(movie_id: String) = flow {
        try {
            emit(Resource.Loading<MovieDetail>())
            val response = api.getMovieDetail(movie_id)
            emit(Resource.Success(response.toMovieDetail()))
        } catch (e: HttpException) {
            emit(Resource.Error<MovieDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<MovieDetail>("Couldn't reach server, check your internet"))
        }
    }

    override fun getConfiguration(): Flow<Resource<ConfigurationDto>> = flow {
        try {
            emit(Resource.Loading<ConfigurationDto>())
            emit(Resource.Success(api.getConfiguration()))
        } catch (e: HttpException) {
            emit(
                Resource.Error<ConfigurationDto>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<ConfigurationDto>("Couldn't reach server, check your internet"))
        }

    }
}