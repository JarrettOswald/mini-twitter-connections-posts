package ru.korpanov.clients.user

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.net.URI

@Configuration
@ConfigurationProperties("endpoint.user")
class UserClientProperties {
    var url : URI = URI("http://localhost:8080")
}