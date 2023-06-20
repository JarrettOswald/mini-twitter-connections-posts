package ru.korpanov.clients.user

import org.openapitools.api.UsersApi
import org.openapitools.model.User
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import ru.korpanov.clients.connection.ConnectionClient
import java.util.*

class UserClient(

) : UsersApi {

    private val logger = LoggerFactory.getLogger(ConnectionClient::class.java)

    private val restTemplate: RestTemplate = RestTemplate()

    override fun findAllUsers(): ResponseEntity<List<User>> {
        return super.findAllUsers()
    }

    override fun getUserByUserId(userId: UUID): ResponseEntity<List<User>> {
        return super.getUserByUserId(userId)
    }

    override fun saveUser(user: User): ResponseEntity<String> {
        return super.saveUser(user)
    }

    override fun updateUserByUserId(userId: UUID, user: User): ResponseEntity<String> {
        return super.updateUserByUserId(userId, user)
    }
}