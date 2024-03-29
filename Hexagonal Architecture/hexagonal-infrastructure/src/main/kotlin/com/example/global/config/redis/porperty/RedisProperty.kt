package com.example.global.config.redis.porperty

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("spring.redis")
@ConstructorBinding
data class RedisProperty(
    val port: Int,
    val host: String
)
