package com.myapplication.domain.use_case.get_configuration

import com.myapplication.domain.repository.remote.MovieRepository
import javax.inject.Inject

class GetConfigurationUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getConfiguration()
}