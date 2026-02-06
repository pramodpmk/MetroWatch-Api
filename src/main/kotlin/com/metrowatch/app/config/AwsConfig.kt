package com.metrowatch.app.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("aws")
class AwsConfig {
    lateinit var region: String
}
