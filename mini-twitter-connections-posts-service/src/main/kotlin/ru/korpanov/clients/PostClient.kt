package ru.korpanov.clients

import org.openapitools.api.PostsApi
import org.openapitools.model.Post
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.util.*

class PostClient : PostsApi {

    private val restTemplate : RestTemplate = RestTemplate()
    override fun findPosts(userId: UUID?): ResponseEntity<List<Post>> {

        return super.findPosts(userId)
    }

    override fun savePost(post: Post): ResponseEntity<String> {
        return super.savePost(post)
    }
}