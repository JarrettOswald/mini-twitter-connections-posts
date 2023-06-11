package ru.korpanov.controllers

import org.openapitools.api.ConnectionsPostsApi
import org.openapitools.model.PostSummary
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import ru.korpanov.clients.post.PostClient

@Controller
class ConnectionsPostsController(private val postClient: PostClient) : ConnectionsPostsApi {

    override fun getConnectionsPost(username: String): ResponseEntity<List<PostSummary>> {
        postClient.findPosts(null)

        return super.getConnectionsPost(username)
    }
}