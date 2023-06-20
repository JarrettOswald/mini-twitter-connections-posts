package ru.korpanov.clients.post

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.openapitools.model.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class PostClientTest {

    @Autowired
    private lateinit var client: PostClient

    @Test
    fun findPosts() {
        val r = client.findPosts(UUID.randomUUID())
        assertTrue(r.statusCode.is2xxSuccessful)
    }

    @Test
    fun savePost() {
        val post = Post(
            userId = UUID.randomUUID(),
            title = "Пыль в городе",
            date = LocalDate.now(),
            body = "Пост про политику"
        )
        val r = client.savePost(post)
        assertTrue(r.statusCode.is2xxSuccessful)
    }

    companion object {
        private val wireMockServer = WireMockServer(8090)

        @JvmStatic
        @BeforeAll
        internal fun beforeAll() {
            wireMockServer.start()
            WireMock.configureFor("localhost", 8090)
        }

        @JvmStatic
        @AfterAll
        internal fun afterAll() {
            wireMockServer.stop()
        }
    }
}