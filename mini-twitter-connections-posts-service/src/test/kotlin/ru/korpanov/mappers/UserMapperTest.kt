package ru.korpanov.mappers

import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.openapitools.model.User
import java.util.*

class UserMapperTest {

    @Test
    fun map() {
        val user = User(
            uuid = UUID.randomUUID(),
            name = "Test_name",
            surname = "TestSurname"
        )
        val mapper = Mappers.getMapper(UserMapper::class.java)
        val userDto = mapper.map(user)
    }
}