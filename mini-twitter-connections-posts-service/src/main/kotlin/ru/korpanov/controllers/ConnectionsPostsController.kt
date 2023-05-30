package ru.korpanov.controllers

import org.openapitools.api.ConnectionsPostsApi
import org.openapitools.model.PostSummary
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class ConnectionsPostsController : ConnectionsPostsApi {

    override fun getConnectionsPost(username: String): ResponseEntity<List<PostSummary>> {
        return super.getConnectionsPost(username)
    }
}