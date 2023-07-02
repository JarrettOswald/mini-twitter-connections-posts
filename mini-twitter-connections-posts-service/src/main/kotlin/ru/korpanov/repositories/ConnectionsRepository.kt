package ru.korpanov.repositories

import org.jooq.Configuration
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.korepanov.minitwitterservice.tables.daos.ConnectionsDao

@Repository
class ConnectionsRepository(private val dslContext: DSLContext, configuration: Configuration) :
    ConnectionsDao(configuration) {

}