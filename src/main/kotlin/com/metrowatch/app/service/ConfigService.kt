package com.metrowatch.app.service

import com.metrowatch.app.dto.ConfigDto
import com.metrowatch.app.dto.ConfigVersionDto

interface ConfigService {

    fun getVersion(): ConfigVersionDto

    fun getConfiguration(): ConfigDto
}