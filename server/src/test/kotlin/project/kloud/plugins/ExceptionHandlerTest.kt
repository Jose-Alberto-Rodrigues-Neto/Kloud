package project.kloud.plugins

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class ExceptionHandlerTest {
    @Test
    fun handleDefaultException() = testApplication {
        // Arrange
        application {
            configureExceptionHandler()
        }
        val request = request {
            url("/bad-route")
        }

        // Act
        val result = client.request(request)

        // Assert
        result.apply {
            assert(status == HttpStatusCode.InternalServerError)
        }
    }
}