package project.kloud.plugins

import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ServerContentNegotiation

class AuthProviderTest {
    @Test
    fun connectWithGoogleProvider() = testApplication {
        // Arrange
        environment {
            config = ApplicationConfig("application.conf")
        }
        val testClient = createClient {
            install(HttpCookies)
            install(ClientContentNegotiation) {
                json()
            }
        }
        application {
            configureSession()
            configureAuthProvider(testClient)
            routing {
                get("/login-test") {
                    call.sessions.set(UserSession("xyz", "123"))
                }
            }
        }
        val info = GoogleUserInfo(
            id = "1234567890",
            name = "John Doe",
            email = "jonh@mail.com",
            familyName = "Doe",
            givenName = "John",
            locale = "en",
            picture = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png"
        )
        externalServices {
            hosts(GOOGLE_HOST) {
                install(ServerContentNegotiation) {
                    json()
                }
                routing {
                    get("oauth2/v2/userinfo") {
                        call.respond(info)
                    }
                }
            }
        }
        testClient.get("/login-test")

        // Act
        val result = testClient.get("/home")

        // Assert
        assertEquals(HttpStatusCode.OK, result.status)
        assertEquals(info.name, result.bodyAsText())
    }
}