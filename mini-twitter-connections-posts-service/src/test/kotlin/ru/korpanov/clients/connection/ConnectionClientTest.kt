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
import java.util.*


@SpringBootTest

class ConnectionClientTest {

    @Autowired
    private lateinit var connectionClient: ConnectionClient

    private val logger = LoggerFactory.getLogger(ConnectionClientTest::class.java)

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
        val randomUUID = UUID.randomUUID()
        val newConnection = Connection(
            uuid = randomUUID, follower = UUID.randomUUID(), followed = UUID.randomUUID()
        )
        val r = connectionClient.saveConnection(newConnection)
        Assertions.assertTrue(r.statusCode.is2xxSuccessful)
    }

    @Test
    fun updateConnectionByUserId() {
        val connections = connectionClient.getConnections().body!!
        val connection = connections[Random().nextInt(connections.size - 1)]
        logger.info(connection.toString())
        val updateConnection = Connection(
            uuid = connection.uuid, follower = UUID.randomUUID(), followed = connection.followed
        )
        val r = connectionClient.updateConnectionByUserId(connection.uuid, updateConnection)
        Assertions.assertTrue(r.statusCode.is2xxSuccessful)
    }


    companion object {
        private val wireMockServer = WireMockServer(8090)

        @JvmStatic
        @BeforeAll
        internal fun beforeAll() {
            wireMockServer.start()
            configureFor("localhost", 8090)
        }

        @JvmStatic
        @AfterAll
        internal fun afterAll() {
            wireMockServer.stop()
        }
    }
}