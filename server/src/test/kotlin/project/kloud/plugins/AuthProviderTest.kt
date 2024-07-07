package project.kloud.plugins

import ch.qos.logback.core.testUtil.EnvUtilForTests
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.config.ConfigLoader.Companion.load
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.testing.*
import io.ktor.util.debug.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.callbackFlow
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ServerContentNegotiation



class AuthProviderTest {
    companion object {
        const val ADD_SESSION_PATH = "/add-session"
    }

    private fun googleInfoByUser(name: String, email: String) =
        GoogleUserInfo(
            id = "1234567890",
            name = name,
            email = email,
            familyName = name.split(" ").last(),
            givenName = "John",
            locale = "en",
            picture = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png"
        )

    context(ApplicationTestBuilder)
    private fun testClient(): HttpClient =
        createClient {
            install(HttpCookies)
            install(ClientContentNegotiation) {
                json()
            }
        }

    context(ApplicationTestBuilder)
    private fun defaultEnvironment() =
        environment {
            config = ApplicationConfig("application.conf")
        }

    context(ApplicationTestBuilder)
    private fun Application.addSessionRoute() =
        routing {
            post<UserSession>(ADD_SESSION_PATH) { userSession ->
                call.sessions.set(userSession)
            }
        }

    context(ApplicationTestBuilder)
    private suspend fun HttpClient.addSession(userSession: UserSession) =
        post(ADD_SESSION_PATH) {
            contentType(ContentType.Application.Json)
            setBody(userSession)
        }

    context(ApplicationTestBuilder)
    private fun setupGoogleClientMock(info: GoogleUserInfo) =
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

    @Test
    fun redirectToLogin() = testApplication {
        // Arrange
        defaultEnvironment()
        val testClient = testClient()
        application {
            configureSession()
            configureAuthProvider(testClient)
        }
        val info = googleInfoByUser( "Jon Doe", "nightwatch@wall.com.ws")
            .also { setupGoogleClientMock(it) }

        // Act
        val result = testClient.get("/")
            .headers[HttpHeaders.Location]

        assertEquals(result, "/login")
    }

    @Test
    fun connectWithGoogleProvider() = testApplication {
        // Arrange
        defaultEnvironment()
        val testClient = testClient()
        application {
            configureSession()
            configureAuthProvider(testClient)
            Serialization()
            addSessionRoute()
        }
        val info = googleInfoByUser(
            name = "John Doe",
            email = "jonh@mail.com"
        ).also { setupGoogleClientMock(it) }

        testClient.addSession(UserSession("xyz", "123"))

        // Act
        val result = testClient.get("/home")

        // Assert
        assertEquals(HttpStatusCode.OK, result.status)
        assertEquals(info.name, result.bodyAsText())
    }
}