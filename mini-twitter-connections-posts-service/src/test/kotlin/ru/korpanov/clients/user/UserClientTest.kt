package ru.korpanov.clients.user

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.openapitools.model.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.korpanov.clients.connection.ConnectionClient
import java.util.*

@SpringBootTest
class UserClientTest {

    @Autowired
    private lateinit var client: UserClient
    private val logger = LoggerFactory.getLogger(UserClientTest::class.java)


    @Test
    fun findAllUsers() {
        val r = client.findAllUsers()
        logger.info(r.body.toString())
        assertTrue(r.statusCode.is2xxSuccessful)
    }

    @Test
    fun getUserByUserId() {
        val r = client.getUserByUserId(UUID.randomUUID())
        logger.info(r.body.toString())
        assertTrue(r.statusCode.is2xxSuccessful)
    }

    @Test
    fun saveUser() {
        val user = User(
            uuid = UUID.randomUUID(),
            name = "Ivan",
            surname = "Drago"
        )
        val r = client.saveUser(user)
        assertTrue(r.statusCode.is2xxSuccessful)
    }

    @Test
    fun updateUserByUserId() {
        val user = User(
            uuid = UUID.randomUUID(),
            name = "Ivan",
            surname = "Drago"
        )
        client.updateUserByUserId(
            userId = UUID.randomUUID(),
            user = user
        )

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