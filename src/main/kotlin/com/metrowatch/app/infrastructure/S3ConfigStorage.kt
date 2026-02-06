package com.metrowatch.app.infrastructure

import com.metrowatch.app.config.AppConfig
import com.metrowatch.app.config.AwsConfig
import com.metrowatch.app.domain.ConfigVersionModel
import jakarta.inject.Singleton
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.HeadObjectRequest
import software.amazon.awssdk.services.s3.model.HeadObjectResponse
import java.time.format.DateTimeFormatter

@Singleton
class S3ConfigStorage(
    private val awsConfig: AwsConfig,
    private val appConfig: AppConfig
) : ConfigStorage {

    val client: S3Client = S3Client.builder().region(
        Region.of(awsConfig.region)
    ).build()
    // warm cache
    @Volatile private var cachedConfig: String? = null
    @Volatile private var cachedVersionId: String? = null

    override fun getVersion(): ConfigVersionModel {
        val head = client.headObject(
            HeadObjectRequest.builder()
                .bucket(appConfig.bucket)
                .key(appConfig.configKey)
                .build()
        )
        return ConfigVersionModel(
            versionName = head.versionId() ?: "unknown",
            lastUpdated = DateTimeFormatter.ISO_INSTANT.format(
                head.lastModified()
            ),
        )
    }

    override fun getConfig(): Pair<String, ConfigVersionModel> {
        val head = client.headObject(
            HeadObjectRequest.builder()
                .bucket(appConfig.bucket)
                .key(appConfig.configKey)
                .build()
        )
        val versionId = head.versionId()
        if (cachedConfig != null && cachedVersionId == versionId) {
            return cachedConfig!! to toVersion(head)
        }
        val response = client.getObject(
            GetObjectRequest.builder()
                .bucket(appConfig.bucket)
                .key(appConfig.configKey)
                .build()
        )
        val json = response.readAllBytes().decodeToString()
        cachedConfig = json
        cachedVersionId = versionId
        return json to toVersion(head)
    }

    private fun toVersion(
        head: HeadObjectResponse
    ) = ConfigVersionModel(
        versionName = head.versionId() ?: "unknown",
        lastUpdated = DateTimeFormatter.ISO_INSTANT.format(
            head.lastModified()
        ),
    )
}