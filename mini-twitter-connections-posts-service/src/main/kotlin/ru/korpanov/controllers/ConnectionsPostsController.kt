package ru.korpanov.controllers

import org.mapstruct.factory.Mappers
import org.openapitools.api.ConnectionsApi
import org.openapitools.api.ConnectionsPostsApi
import org.openapitools.api.PostsApi
import org.openapitools.api.UsersApi
import org.openapitools.model.Connection
import org.openapitools.model.Post
import org.openapitools.model.PostSummary
import org.openapitools.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import ru.korpanov.mappers.UserMapper
import ru.korpanov.repositories.UsersRepository
import kotlin.math.log

@Controller
class ConnectionsPostsController(
    private val usersRepository: UsersRepository

) : ConnectionsPostsApi, ConnectionsApi, PostsApi, UsersApi {

    val logger = LoggerFactory.getLogger(ConnectionsPostsController::class.java)
    override fun getConnectionsPost(username: String): ResponseEntity<List<PostSummary>> {
        return super.getConnectionsPost(username)
    }

    override fun saveConnection(connection: Connection): ResponseEntity<String> {
        return super.saveConnection(connection)
    }

    override fun savePost(post: Post): ResponseEntity<String> {
        return super.savePost(post)
    }

    override fun saveUser(user: User): ResponseEntity<String> {
        logger.info(user.toString())
        val mapper = Mappers.getMapper(UserMapper::class.java)
        val userDto = mapper.map(user)
        usersRepository.insert(userDto)
        return ResponseEntity("Saved user", HttpStatus.OK)
    }
}