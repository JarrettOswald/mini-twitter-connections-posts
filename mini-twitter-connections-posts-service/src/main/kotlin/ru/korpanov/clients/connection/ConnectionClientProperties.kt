package ru.korpanov.clients.connection

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.net.URI
@Configuration
@ConfigurationProperties("endpoint.connection")
data class ConnectionClientProperties(
    var url : URI = URI("http://localhost:8080")
)