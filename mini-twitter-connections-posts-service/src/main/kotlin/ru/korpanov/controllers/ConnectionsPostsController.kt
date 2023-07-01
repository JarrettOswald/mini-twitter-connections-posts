package ru.korpanov.controllers

import org.openapitools.api.ConnectionsApi
import org.openapitools.api.ConnectionsPostsApi
import org.openapitools.api.PostsApi
import org.openapitools.api.UsersApi
import org.openapitools.model.Connection
import org.openapitools.model.Post
import org.openapitools.model.PostSummary
import org.openapitools.model.User
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class ConnectionsPostsController(
) : ConnectionsPostsApi, ConnectionsApi, PostsApi, UsersApi {
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
        return super.saveUser(user)
    }
}