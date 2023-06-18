package ru.korpanov.clients.connection


import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.openapitools.model.Connection
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.util.*


@SpringBootTest

class ConnectionClientTest {

    @Autowired
    private lateinit var connectionClient: ConnectionClient

    private val logger = LoggerFactory.getLogger(ConnectionClientTest::class.java)

    @Test
    fun wireMock() {
        val restTemplate = RestTemplate()
        val r = restTemplate.exchange(
            "http://localhost:8090/connections",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            String::class.java
        )
        logger.info(r.body)
    }

    @Test
    fun getConnections() {
        val resp = connectionClient.getConnections()
        Assertions.assertTrue(resp.body!!.isNotEmpty())
    }

    @Test
    fun getFollowedConnectionByUserName() {
        val connections = connectionClient.getConnections().body
        val connection = connections?.get(Random().nextInt(connections.size - 1))
        val f = connectionClient.getFollowedConnectionByUserName(connection!!.follower)
        Assertions.assertTrue(f.body!!.isNotEmpty())
    }

    @Test
    fun getFollowerConnectionByUserName() {
        val connections = connectionClient.getConnections().body
        val connection = connections?.get(Random().nextInt(connections.size - 1))
        val f = connectionClient.getFollowerConnectionByUserName(connection!!.follower)
        Assertions.assertTrue(f.body!!.isNotEmpty())
    }

    @Test
    fun saveConnection() {
        val connectionsSizeOld = connectionClient.getConnections().body!!.size
        val randomUUID = UUID.randomUUID()
        val newConnection = Connection(
            uuid = randomUUID, follower = UUID.randomUUID(), followed = UUID.randomUUID()
        )
        connectionClient.saveConnection(newConnection)
        val connectionsSizeNew = connectionClient.getConnections().body!!.size
        Assertions.assertTrue(connectionsSizeOld < connectionsSizeNew)

        //todo добавить очистку
    }

    @Test
    fun updateConnectionByUserId() {
        var connections = connectionClient.getConnections().body
        val connection = connections?.get(Random().nextInt(connections.size - 1))!!
        logger.info(connection.toString())
        val updateConnection = Connection(
            uuid = connection.uuid, follower = UUID.randomUUID(), followed = connection.followed
        )
        connectionClient.updateConnectionByUserId(connection.uuid, updateConnection)
        connections = connectionClient.getConnections().body!!
        val connectionNew = connections.first { it.uuid == connection.uuid }
        Assertions.assertNotEquals(connection, connectionNew)
    }


    companion object {
        private val wireMockServer = WireMockServer(8090)

        @JvmStatic
        @BeforeAll
        internal fun beforeAll(): Unit {
            wireMockServer.start()
            configureFor("localhost", 8090)
        }


        @JvmStatic
        @AfterAll
        internal fun afterAll(): Unit {
            wireMockServer.stop()
        }
    }
}