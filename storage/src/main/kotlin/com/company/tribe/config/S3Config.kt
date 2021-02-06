package com.company.tribe.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {
    @Bean
    fun amazonS3(
        @Value("\${aws.region}") regionName: String,
        @Value("\${aws.s3.endpoint}") endpoint: String,
        @Value("\${aws.s3.access-key}") accessKey: String,
        @Value("\${aws.s3.secret-key}") secretKey: String,
        @Value("\${aws.s3.path-style-access-enabled}") pathStyleAccessEnabled: Boolean
    ): AmazonS3 {
        val credentials = BasicAWSCredentials(accessKey, secretKey)
        val credentialsProvider = AWSStaticCredentialsProvider(credentials)
        val endpointConfiguration = AwsClientBuilder.EndpointConfiguration(endpoint, regionName)

        return AmazonS3ClientBuilder
            .standard()
            .withEndpointConfiguration(endpointConfiguration)
            .withCredentials(credentialsProvider)
            .withPathStyleAccessEnabled(pathStyleAccessEnabled)
            .build()
    }
}
