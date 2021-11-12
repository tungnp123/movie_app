package com.myapplication.domain.use_case.local_configuration

import com.myapplication.domain.repository.local.ConfigurationRepository
import javax.inject.Inject

class SetLocalConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    suspend operator fun invoke(value: String) = configurationRepository.putConfiguration(value)
}