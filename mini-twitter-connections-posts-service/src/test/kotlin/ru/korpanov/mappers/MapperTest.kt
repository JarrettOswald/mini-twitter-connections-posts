package ru.korpanov.mappers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.openapitools.model.Connection
import org.openapitools.model.Post
import org.openapitools.model.User
import java.time.LocalDate
import java.util.*

class MapperTest {

    @Test
    fun mapToUserTest() {
        val user = User(
            uuid = UUID.randomUUID(), name = "Test_name", surname = "TestSurname"
        )
        val mapper = Mappers.getMapper(UserMapper::class.java)
        val userDto = mapper.toJooqUser(user)
        Assertions.assertEquals(userDto.id, user.uuid)
    }

    @Test
    fun mapToPost() {
        val post = Post(
            userId = UUID.randomUUID(), title = "test", date = LocalDate.now(), "test text"
        )
        val mapper = Mappers.getMapper(PostMapper::class.java)
        val postJooq = mapper.toJooqPost(post)
        Assertions.assertEquals(post.date, postJooq.postCreate)
    }

    @Test
    fun mapToConnection() {
        val connection = Connection(
            uuid = UUID.randomUUID(), follower = UUID.randomUUID(), followed = UUID.randomUUID()
        )
        val mapper = Mappers.getMapper(ConnectionMapper::class.java)
        val connectionJooq = mapper.toJooqConnection(connection)
        Assertions.assertEquals(connection.uuid, connectionJooq.id)

    }
}