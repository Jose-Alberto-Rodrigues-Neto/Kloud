package project.kloud

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class IsAliveTest {

    @Test
    fun testGetIsAlive() = testApplication {
        // Arrange
        application {
            isAlive()
        }
        val request = request {
            url("/is-alive")
            parameter("ping", "1")
        }

        // Act
        val result = client.request(request)

        // Assert
        result.apply {
            assert(status == HttpStatusCode.OK)
            assertEquals("pong 2", bodyAsText())
        }
    }

    @Test
    fun testGetIsAliveWithoutPing() = testApplication {
        // Arrange
        application {
            isAlive()
        }
        val request = request {
            url("/is-alive")
            parameter("ping", "not a number")
        }

        // Act
        val result = client.request(request)

        // Assert
        result.apply {
            val (_, message) = bodyAsText().split(":")

            assertEquals(status, HttpStatusCode.BadRequest)
            assertContains(PING_PARAMETER_EXCEPTION_MESSAGE, message.trim())
        }
    }
}