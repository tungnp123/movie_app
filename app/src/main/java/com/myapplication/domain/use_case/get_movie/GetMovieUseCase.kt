package com.myapplication.domain.use_case.get_movie

import com.myapplication.domain.repository.remote.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movie_id: String) = repository.getMovieDetail(movie_id)
}