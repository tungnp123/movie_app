package com.myapplication.domain.use_case.get_movies

import com.myapplication.common.Resource
import com.myapplication.domain.model.MovieList
import com.myapplication.domain.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(path: String, page: Int): Flow<Resource<MovieList>> =
        repository.getListMovies(path, page)
}