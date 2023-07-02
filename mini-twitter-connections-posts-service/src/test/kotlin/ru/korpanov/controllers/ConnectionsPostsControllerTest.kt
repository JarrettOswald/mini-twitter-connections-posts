package ru.korpanov.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeAll
import org.openapitools.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class ConnectionsPostsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getConnectionsPost() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/connections-posts/7fb54d02-de1c-11ed-b5ea-0242ac120002")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun saveUser() {
        val user = User(
            uuid = UUID.randomUUID(),
            name = "Test_name",
            surname = "TestSurname"
        )

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/users")
                .content(objectMapper.writeValueAsString(user))
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    companion object {
        private val objectMapper: ObjectMapper = ObjectMapper()
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