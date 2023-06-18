package ru.korpanov.clients.connection


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openapitools.model.Connection
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class ConnectionClientTest {

    private val logger = LoggerFactory.getLogger(ConnectionClientTest::class.java)

    @Autowired
    private lateinit var connectionClient: ConnectionClient

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
            uuid = randomUUID,
            follower = UUID.randomUUID(),
            followed = UUID.randomUUID()
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
            uuid = connection.uuid,
            follower = UUID.randomUUID(),
            followed = connection.followed
        )
        connectionClient.updateConnectionByUserId(connection.uuid, updateConnection)
        connections = connectionClient.getConnections().body!!
        val connectionNew = connections.filter { it.uuid == connection.uuid }.first()
        Assertions.assertNotEquals(connection, connectionNew)
    }
}