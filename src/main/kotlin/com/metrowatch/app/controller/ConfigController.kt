package com.metrowatch.app.controller

import com.metrowatch.app.dto.ConfigDto
import com.metrowatch.app.dto.ConfigVersionDto
import com.metrowatch.app.service.ConfigService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/config")
class ConfigController(private val configService: ConfigService) {

    @Get("/version")
    fun configVersion(): ConfigVersionDto {
        return configService.getVersion()
    }

    @Get
    fun readConfig(): ConfigDto {
        return configService.getConfiguration()
    }
}
