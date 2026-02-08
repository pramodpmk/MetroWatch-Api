package com.metrowatch.app.error

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ApiError(
    val timestamp: Long = System.currentTimeMillis(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String?
)
