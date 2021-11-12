package com.myapplication.data.repository.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.myapplication.common.Constants
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.domain.repository.local.ConfigurationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.myDataStore by preferencesDataStore("settings")

class ConfigurationRepositoryImpl(private val context: Context) : ConfigurationRepository {
    override suspend fun putConfiguration(value: String) {
        val dataStoreKey = stringPreferencesKey(Constants.CONFIGURATION)
        context.myDataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    override fun getConfiguration(): Flow<ConfigurationDto?> =
        context.myDataStore.data.map { preference ->
            try {
                Gson().fromJson(
                    preference[stringPreferencesKey(Constants.CONFIGURATION)]
                        ?: "", ConfigurationDto::class.java
                )
            } catch (e: Exception) {
                null
            }
        }

}