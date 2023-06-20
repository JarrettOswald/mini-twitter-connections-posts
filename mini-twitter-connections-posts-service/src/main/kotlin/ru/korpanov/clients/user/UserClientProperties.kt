package ru.korpanov.clients.user

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("endpoint.user")
class UserClientProperties {
}