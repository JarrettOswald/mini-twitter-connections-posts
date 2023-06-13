package ru.korpanov.clients.post

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.net.URI

@Configuration
@ConfigurationProperties("endpoint.post")
data class PostClientProperties(
    var url : URI = URI("http://localhost:8080")
)