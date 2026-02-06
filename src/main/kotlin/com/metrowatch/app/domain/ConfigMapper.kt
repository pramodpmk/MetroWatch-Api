package com.metrowatch.app.domain

import com.metrowatch.app.dto.ConfigDto
import com.metrowatch.app.dto.ConfigVersionDto

object ConfigVersionMapper {

    fun domainToDto(
        configVersionModel: ConfigVersionModel
    ): ConfigVersionDto {
        return ConfigVersionDto(
            version = configVersionModel.versionName,
            updatedAt = configVersionModel.lastUpdated
        )
    }
}

object ConfigMapper {

    fun domainToDto(
        config: Pair<String, ConfigVersionModel>
    ): ConfigDto {
        return ConfigDto(
            configuration = config.first,
            version = config.second.versionName,
        )
    }
}