package ru.korpanov.clients.post

import org.openapitools.api.PostsApi
import org.openapitools.model.Post
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*

@Component
class PostClient(
    private val properties: PostClientProperties
) : PostsApi {

    private val restTemplate: RestTemplate = RestTemplate()
    override fun findPosts(userId: UUID?): ResponseEntity<List<Post>> {
        val requestEntity = HttpEntity.EMPTY
        return (restTemplate.exchange(
            "${properties.url}/posts",
            HttpMethod.GET,
            requestEntity,
            ResponseEntity::class.java
        ).body as ResponseEntity<List<Post>>?)!!
    }

    override fun savePost(post: Post): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestEntity = HttpEntity(post, headers)
        restTemplate.postForEntity(
            "${properties.url}/posts",
            requestEntity,
            ResponseEntity::class.java
        )
        return ResponseEntity("Ok", HttpStatus.OK)
    }
}