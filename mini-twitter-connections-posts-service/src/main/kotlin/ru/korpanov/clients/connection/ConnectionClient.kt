package ru.korpanov.clients.connection

import org.openapitools.api.ConnectionsApi
import org.openapitools.model.Connection
import org.openapitools.model.Post
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*

@Component
class ConnectionClient(
    private val properties: ConnectionClientProperties
) : ConnectionsApi {

    private val restTemplate: RestTemplate = RestTemplate()
    override fun getConnections(): ResponseEntity<List<Connection>> {
        val requestEntity = HttpEntity.EMPTY
        return (restTemplate.exchange(
            "${properties.url}/connections",
            HttpMethod.GET,
            requestEntity,
            ResponseEntity::class.java
        ).body as ResponseEntity<List<Connection>>?)!!
    }

    override fun getFollowedConnectionByUserName(userId: UUID): ResponseEntity<List<Connection>> {
        val requestEntity = HttpEntity.EMPTY
        return (restTemplate.exchange(
            "${properties.url}/connections/${userId}/followed",
            HttpMethod.GET,
            requestEntity,
            ResponseEntity::class.java
        ).body as ResponseEntity<List<Connection>>?)!!
    }

    override fun getFollowerConnectionByUserName(userId: UUID): ResponseEntity<List<Connection>> {
        val requestEntity = HttpEntity.EMPTY
        return (restTemplate.exchange(
            "${properties.url}/connections/${userId}/follower",
            HttpMethod.GET,
            requestEntity,
            ResponseEntity::class.java
        ).body as ResponseEntity<List<Connection>>?)!!
    }

    override fun saveConnection(connection: Connection): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestEntity = HttpEntity(connection, headers)
        restTemplate.postForEntity(
            "${properties.url}/connections",
            requestEntity,
            ResponseEntity::class.java
        )
        return ResponseEntity("Ok", HttpStatus.OK)
    }

    override fun updateConnectionByUserId(userId: UUID, connection: Connection): ResponseEntity<String> {
        val headers = HttpHeaders()
        val requestEntity = HttpEntity(connection, headers)
        restTemplate.exchange(
            "${properties.url}/connections/${userId}",
            HttpMethod.PUT,
            requestEntity,
            ResponseEntity::class.java)
        return ResponseEntity("Ok", HttpStatus.OK)
    }
}