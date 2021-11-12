package com.myapplication.domain.repository.local

import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun putConfiguration(value: String)

    fun getConfiguration(): Flow<ConfigurationDto?>
}