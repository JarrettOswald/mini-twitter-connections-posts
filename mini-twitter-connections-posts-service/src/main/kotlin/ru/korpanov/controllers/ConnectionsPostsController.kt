package ru.korpanov.controllers

import org.openapitools.api.ConnectionsPostsApi
import org.openapitools.model.Post
import org.openapitools.model.PostSummary
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import ru.korpanov.clients.connection.ConnectionClient
import ru.korpanov.clients.post.PostClient
import java.util.*

@Controller
class ConnectionsPostsController(
    private val postClient: PostClient,
    private val connectionClient: ConnectionClient
) : ConnectionsPostsApi {

    override fun getConnectionsPost(username: String): ResponseEntity<List<PostSummary>> {
        val posts = mutableListOf<Post>()
        val connections = connectionClient.getFollowerConnectionByUserName(UUID.fromString(username)).body!!
        connections
            .filter { it.follower == UUID.fromString(username) }
            .map { postClient.findPosts(it.followed).body!! }
            .forEach { posts.addAll(it) }

        return super.getConnectionsPost(username)
    }
}