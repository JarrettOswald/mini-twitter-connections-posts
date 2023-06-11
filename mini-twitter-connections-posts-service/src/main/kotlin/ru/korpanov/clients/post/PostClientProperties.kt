package ru.korpanov.clients.post

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component
import java.net.URI


@Component
@ConfigurationProperties("endpoint.post")
data class PostClientProperties(
    val url : URI
)