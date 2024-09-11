package project.kloud

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import project.kloud.config.TestUtils.expectResponse
import project.kloud.domain.PING_PARAMETER_EXCEPTION_MESSAGE
import project.kloud.domain.Pong
import java.util.*
import kotlin.test.Test
import kotlin.test.assertContains

class IsAliveTest {

    @Test
    fun `When ping is missing should respond with bad request`() = testApplication {
        // Arrange
        val request = request {
            method = HttpMethod.Get
            url("/is-alive?")
        }

        // Act & Assert
        client.expectResponse(HttpStatusCode.BadRequest, request) { response ->
            assertContains(response.bodyAsText(), PING_PARAMETER_EXCEPTION_MESSAGE)
        }
    }

    @Test
    fun `Should respond ping with pong`() = testApplication {
        // Arrange
        val request = request {
            method = HttpMethod.Get
            contentType(ContentType.Application.Json)
            url("/is-alive?ping=1")
        }
        val now = Date().toInstant().plusSeconds(30L)

        // Act & Assert
        client.expectResponse(HttpStatusCode.OK, request) { response ->
            val body = response.bodyAsText()
                .let { Json.decodeFromString(Pong.serializer(), it) }

            assert(body.applicationStart.isBefore(now))
        }
    }
}