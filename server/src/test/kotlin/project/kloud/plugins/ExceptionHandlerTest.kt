package project.kloud.plugins

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import project.kloud.PING_PARAMETER_EXCEPTION_MESSAGE
import kotlin.test.Test
import kotlin.test.assertEquals

class ExceptionHandlerTest {
    @Test
    fun handleRouteNotFound() = testApplication {
        // Arrange
        val request = request {
            url("/bad-route")
        }

        // Act
        val result = client.request(request)

        // Assert
        assertEquals(result.status, HttpStatusCode.NotFound)
    }

    @Test
    fun handleIllegalArgumentException() = testApplication {
        // Arrange
        val request = request {
            url("/is-alive")
            parameter("ping", "not a number")
        }

        // Act
        val result = client.request(request)

        // Assert
        result.apply {
            assertEquals(expected = HttpStatusCode.BadRequest, actual = status)
            assert(bodyAsText().trim().contains(PING_PARAMETER_EXCEPTION_MESSAGE))
        }
    }
}