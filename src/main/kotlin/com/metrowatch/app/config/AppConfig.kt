package com.metrowatch.app.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("app")
class AppConfig {
    lateinit var bucket: String
    lateinit var configKey: String
}
