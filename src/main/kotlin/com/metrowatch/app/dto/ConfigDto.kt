package com.metrowatch.app.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ConfigDto(
    val configuration: String,
    val version: String,
)
