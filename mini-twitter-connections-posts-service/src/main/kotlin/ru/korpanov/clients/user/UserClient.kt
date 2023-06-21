package ru.korpanov.clients.user

import org.openapitools.api.UsersApi
import org.openapitools.model.User
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*

@Component
class UserClient(
    private val properties: UserClientProperties
) : UsersApi {

    private val logger = LoggerFactory.getLogger(UserClient::class.java)

    private val restTemplate: RestTemplate = RestTemplate()

    override fun findAllUsers(): ResponseEntity<List<User>> {
        return restTemplate.exchange(
            "${properties.url}/users",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<User>>() {})
    }

    override fun getUserByUserId(userId: UUID): ResponseEntity<List<User>> {
        return restTemplate.exchange(
            "${properties.url}/users/${userId}",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<User>>() {})
    }

    override fun saveUser(user: User): ResponseEntity<String> {
        restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add("Accept", "*/*")
        val requestEntity = HttpEntity(user, headers)

        val r = restTemplate.postForEntity(
            "${properties.url}/users",
            requestEntity,
            String::class.java
        )
        return ResponseEntity(r.body, HttpStatus.OK)
    }

    override fun updateUserByUserId(userId: UUID, user: User): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.add("Accept", "*/*")
        val requestEntity = HttpEntity(user, headers)
        val r = restTemplate.exchange(
            "${properties.url}/users/${userId}",
            HttpMethod.PUT,
            requestEntity,
            String::class.java
        )
        return ResponseEntity(r.body, HttpStatus.OK)
    }
}