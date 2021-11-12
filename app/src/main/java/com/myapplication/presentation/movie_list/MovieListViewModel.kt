package com.myapplication.presentation.movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.common.Constants
import com.myapplication.common.Resource
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.domain.model.Movie
import com.myapplication.domain.use_case.get_movies.GetMoviesUseCase
import com.myapplication.domain.use_case.local_configuration.GetLocalConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getLocalConfigurationUseCase: GetLocalConfigurationUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = _movies
    private val _error = mutableStateOf("")
    val error: State<String> = _error
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _configure = mutableStateOf<ConfigurationDto?>(null)
    val configure: State<ConfigurationDto?> = _configure

    val page = mutableStateOf(1)

    private var listScrollPosition = 0
    private var total_pages: Int = 0
    private var total_results: Int = 0
    var page_size: Int = 0

    var path: String? = null
        set(value) {
            if (field != value) {
                field = value
                getListMovies()
            }
        }

    init {
        getLocalConfiguration()
    }

    private fun getLocalConfiguration() {
        getLocalConfigurationUseCase().onEach { config ->
            _configure.value = config
        }.launchIn(viewModelScope)
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeListPosition(position: Int) {
        listScrollPosition = position
    }

    private fun appendMovies(movies: List<Movie>) {
        val current = ArrayList(_movies.value)
        current.addAll(movies)
        _movies.value = current
    }

    fun resetListState() {
        page.value = 1
        _movies.value = emptyList()
        onChangeListPosition(0)
    }

    fun nextPage() {
        if ((listScrollPosition + 1) >= Constants.calculateMovieListSize(page.value * page_size)
            && page.value <= total_pages
        ) {
            incrementPage()
            getListMovies()
        }
    }

    fun getListMovies() {
        path?.let {
            getMoviesUseCase(path = path!!, page.value).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        appendMovies(result.data?.results ?: emptyList())
                        _isLoading.value = false
                        total_pages = result.data?.total_pages ?: 1
                        total_results = result.data?.total_results ?: 0
                        val currentPageSize = result.data?.results?.size ?: 0
                        page_size = if (page_size < currentPageSize) currentPageSize else page_size
                    }
                    is Resource.Error -> {
                        _error.value = result.message ?: "An unexpected error occurred"

                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
