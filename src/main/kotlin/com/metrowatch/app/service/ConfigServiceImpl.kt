package com.metrowatch.app.service

import com.metrowatch.app.domain.ConfigMapper
import com.metrowatch.app.domain.ConfigVersionMapper
import com.metrowatch.app.dto.ConfigDto
import com.metrowatch.app.dto.ConfigVersionDto
import com.metrowatch.app.util.S3Provider
import jakarta.inject.Singleton

@Singleton
class ConfigServiceImpl(
    val s3Provider: S3Provider
) : ConfigService {

    override fun getVersion(): ConfigVersionDto {
        val versionResult = try {
            ConfigVersionMapper.domainToDto(
                s3Provider.getVersion()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Reading from S3 interrupted")
        }
        return versionResult
    }

    override fun getConfiguration(): ConfigDto {
        val versionResult = try {
            ConfigMapper.domainToDto(
                s3Provider.getConfig()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Reading from S3 interrupted")
        }
        return versionResult
    }
}