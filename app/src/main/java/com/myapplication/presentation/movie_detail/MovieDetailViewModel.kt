package com.myapplication.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.common.Constants
import com.myapplication.common.Resource
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.domain.use_case.get_movie.GetMovieUseCase
import com.myapplication.domain.use_case.local_configuration.GetLocalConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getLocalConfigurationUseCase: GetLocalConfigurationUseCase,
    private val getMovieUseCase: GetMovieUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    private val _configure = mutableStateOf<ConfigurationDto?>(null)
    val configure: State<ConfigurationDto?> = _configure

    init {
        getMovieDetail()
        getLocalConfiguration()
    }

    private fun getLocalConfiguration() {
        getLocalConfigurationUseCase().onEach { config ->
            _configure.value = config
        }.launchIn(viewModelScope)
    }

    private fun getMovieDetail() {
        savedStateHandle.get<String>(Constants.PARAM_MOVIE_ID)?.let { movie_id ->
            getMovieUseCase(movie_id).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = MovieDetailState(movie = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = MovieDetailState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = MovieDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}