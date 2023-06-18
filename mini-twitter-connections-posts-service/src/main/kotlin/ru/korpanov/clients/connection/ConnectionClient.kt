package ru.korpanov.clients.connection

import org.openapitools.api.ConnectionsApi
import org.openapitools.model.Connection
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*


@Component
class ConnectionClient(
    private val properties: ConnectionClientProperties
) : ConnectionsApi {

    private val logger = LoggerFactory.getLogger(ConnectionClient::class.java)

    private val restTemplate: RestTemplate = RestTemplate()


    override fun getConnections(): ResponseEntity<List<Connection>> {
        return restTemplate.exchange(
            "${properties.url}/connections",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<Connection>>() {})
    }

    override fun getFollowedConnectionByUserName(userId: UUID): ResponseEntity<List<Connection>> {
        return restTemplate.exchange(
            "${properties.url}/connections/${userId}/followed",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<Connection>>() {})
    }

    override fun getFollowerConnectionByUserName(userId: UUID): ResponseEntity<List<Connection>> {
        return restTemplate.exchange(
            "${properties.url}/connections/${userId}/follower",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<Connection>>() {})
    }

    override fun saveConnection(connection: Connection): ResponseEntity<String> {
        restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add("Accept", "*/*")
        val requestEntity = HttpEntity(connection, headers)
        val r = restTemplate.postForEntity(
            "${properties.url}/connections",
            requestEntity,
            String::class.java
        )
        logger.info(r.body)
        return ResponseEntity(r.body, HttpStatus.OK)
    }

    override fun updateConnectionByUserId(userId: UUID, connection: Connection): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.add("Accept", "*/*")
        val requestEntity = HttpEntity(connection, headers)
        val r = restTemplate.exchange(
            "${properties.url}/connections/${userId}",
            HttpMethod.PUT,
            requestEntity,
            String::class.java
        )
        return ResponseEntity(r.body, HttpStatus.OK)
    }
}