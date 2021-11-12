package com.myapplication.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.myapplication.common.Resource
import com.myapplication.domain.use_case.get_configuration.GetConfigurationUseCase
import com.myapplication.domain.use_case.local_configuration.GetLocalConfigurationUseCase
import com.myapplication.domain.use_case.local_configuration.SetLocalConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setLocalConfigurationUseCase: SetLocalConfigurationUseCase,
    private val getLocalConfigurationUseCase: GetLocalConfigurationUseCase,
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {

    init {
        getConfiguration()
    }

    private fun getConfiguration() {
        getLocalConfigurationUseCase().onEach { result ->
            if (result == null) {
                getConfigurationUseCase().onEach { _result ->
                    if (_result is Resource.Success) {
                        viewModelScope.launch {
                            setLocalConfigurationUseCase(Gson().toJson(_result.data!!))
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }.launchIn(viewModelScope)
    }
}
