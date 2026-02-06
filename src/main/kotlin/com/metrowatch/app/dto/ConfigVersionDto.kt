package com.metrowatch.app.dto
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ConfigVersionDto(
    val version: String,
    val updatedAt: String,
)
