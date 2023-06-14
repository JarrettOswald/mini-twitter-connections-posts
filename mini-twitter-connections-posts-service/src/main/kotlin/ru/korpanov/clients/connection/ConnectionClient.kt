package ru.korpanov.clients.connection

import org.openapitools.api.ConnectionsApi
import org.openapitools.model.Connection
import org.springframework.http.ResponseEntity
import java.util.*


class ConnectionClient : ConnectionsApi {
    override fun getConnections(): ResponseEntity<List<Connection>> {
        return super.getConnections()
    }

    override fun getFollowedConnectionByUserName(userId: UUID): ResponseEntity<List<Connection>> {
        return super.getFollowedConnectionByUserName(userId)
    }

    override fun getFollowerConnectionByUserName(userId: UUID): ResponseEntity<List<Connection>> {
        return super.getFollowerConnectionByUserName(userId)
    }

    override fun saveConnection(connection: Connection): ResponseEntity<String> {
        return super.saveConnection(connection)
    }

    override fun updateConnectionByUserId(userId: UUID, connection: Connection): ResponseEntity<String> {
        return super.updateConnectionByUserId(userId, connection)
    }
}