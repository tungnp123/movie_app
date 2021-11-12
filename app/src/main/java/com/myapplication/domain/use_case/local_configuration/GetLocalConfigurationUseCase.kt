package com.myapplication.domain.use_case.local_configuration

import com.myapplication.domain.repository.local.ConfigurationRepository
import javax.inject.Inject

class GetLocalConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    operator fun invoke() = configurationRepository.getConfiguration()
}