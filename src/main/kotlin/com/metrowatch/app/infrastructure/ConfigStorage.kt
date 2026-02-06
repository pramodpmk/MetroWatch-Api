package com.metrowatch.app.infrastructure

import com.metrowatch.app.domain.ConfigVersionModel

interface ConfigStorage {

    fun getVersion(): ConfigVersionModel

    fun getConfig(): Pair<String, ConfigVersionModel>
}