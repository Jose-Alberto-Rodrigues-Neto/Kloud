package project.kloud.plugins

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlin.test.Test

class AuthProviderTest {
    @Test
    fun connectWithGoogleProvider() = testApplication {
        // Arrange
        environment {
            ApplicationConfig("application.conf")
        }
        val client = createClient {
            install(HttpCookies)
            install(ContentNegotiation) {
                json()
            }
        }
        application {
            configureAuthProvider(client)
        }

    }
}