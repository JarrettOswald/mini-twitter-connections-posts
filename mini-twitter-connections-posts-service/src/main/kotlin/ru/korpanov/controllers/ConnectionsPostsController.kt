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
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import ru.korpanov.mappers.ConnectionMapper
import ru.korpanov.mappers.PostMapper
import ru.korpanov.mappers.UserMapper
import ru.korpanov.repositories.ConnectionsRepository
import ru.korpanov.repositories.PostRepository
import ru.korpanov.repositories.UsersRepository

@Controller
class ConnectionsPostsController(
    private val usersRepository: UsersRepository,
    private val postRepository: PostRepository,
    private val connectionsRepository: ConnectionsRepository

) : ConnectionsPostsApi, ConnectionsApi, PostsApi, UsersApi {

    val logger = LoggerFactory.getLogger(ConnectionsPostsController::class.java)
    override fun getConnectionsPost(username: String): ResponseEntity<List<PostSummary>> {
        return super.getConnectionsPost(username)
    }

    override fun saveConnection(connection: Connection): ResponseEntity<String> {
        val mapper = Mappers.getMapper(ConnectionMapper::class.java)
        val connectionJooq = mapper.toJooqConnection(connection)
        connectionsRepository.insert(connectionJooq)
        return ResponseEntity("Saved connection", HttpStatus.OK)
    }

    override fun savePost(post: Post): ResponseEntity<String> {
        val mapper = Mappers.getMapper(PostMapper::class.java)
        val postJooq = mapper.toJooqPost(post)
        postRepository.insert(postJooq)
        return ResponseEntity("Saved post", HttpStatus.OK)
    }

    override fun saveUser(user: User): ResponseEntity<String> {
        val mapper = Mappers.getMapper(UserMapper::class.java)
        val userDto = mapper.toJooqUser(user)
        usersRepository.insert(userDto)
        return ResponseEntity("Saved user", HttpStatus.OK)
    }
}