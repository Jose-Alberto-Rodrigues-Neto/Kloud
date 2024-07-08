package project.kloud.plugins

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.testing.*
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
            install(ServerContentNegotiation) {
                json()
            }

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

    @Test
    fun redirectToLogin() = testApplication {
        // Arrange
        defaultEnvironment()
        val testClient = testClient()
        application {
            configureSession()
            configureAuthProvider()
            Serialization()
            addSessionRoute()
        }
        ApplicationConfig("application.conf")
            .config("ktor.security.google.apis.userInfo").apply {
                val host = property("host").getString()
                val path = property("path").getString()

                externalServices {
                    hosts(host) {
                        install(ServerContentNegotiation) {
                            json()
                        }
                        routing {
                            get(path) {
                                call.respond(HttpStatusCode.OK)
                            }
                        }
                    }
                }
            }


        // Act
        val result = testClient
            .apply { addSession(UserSession("token", "state")) }
            .get("/home")
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
            configureAuthProvider()
            addSessionRoute()
        }
        val info = googleInfoByUser(
            name = "John Doe",
            email = "jonh@mail.com"
        ).also { info ->
            ApplicationConfig("application.conf")
                .config("ktor.security.google.apis.userInfo").apply {
                    val host = property("host").getString()
                    val path = property("path").getString()

                    externalServices {
                        hosts(host) {
                            install(ServerContentNegotiation) {
                                json()
                            }
                            routing {
                                get(path) {
                                    call.respond(info)
                                }
                            }
                        }
                    }
                }
        }
        testClient.addSession(UserSession("token", "state"))

        // Act
        val result = testClient.get(" http://localhost/oauth2/v2/userinfo")

        // Assert
        assertEquals(HttpStatusCode.OK, result.status)
        assertEquals(info, result.body())
    }
}