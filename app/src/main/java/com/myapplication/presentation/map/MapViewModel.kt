package com.myapplication.presentation.map

import android.location.Location
import androidx.lifecycle.ViewModel
import com.myapplication.domain.use_case.get_configuration.GetConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getConfigurationUseCase: GetConfigurationUseCase
) : ViewModel() {

    val location = MutableStateFlow<Location?>(null)

    fun setLocation(loc: Location){
        location.value = loc
    }
}