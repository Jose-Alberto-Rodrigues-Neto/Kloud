package project.kloud.plugins

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val applicationHttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            isLenient = true
            prettyPrint = true
        })
    }
}

data class UserSession(val state: String, val token: String)

@Serializable
data class GoogleUserInfo(
    val id: String,
    val name: String,
    val email: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String,
    val picture: String,
    val locale: String,
)

@JvmInline
value class Redirect(val map: MutableMap<String, String>) {
    operator fun get(state: String): String? = map[state]

    operator fun set(state: String, call: ApplicationCall) =
        call.request.queryParameters["redirectUrl"]
            ?.let { map[state] = it }
}

suspend fun checkUserSession(
    call: ApplicationCall,
    block: suspend ApplicationCall.(UserSession) -> Unit = {}
): Unit =
    call.run {
        // TODO: Test this side effect
        // TODO: Test this execution flow on the null branch
        sessions.get<UserSession>()?.let { block(it) } ?:

        // TODO: Test this side effect
        respondRedirect {
            path(LOGIN_URL)
            parameters.append("redirectUrl", request.uri)
        }
    }

const val PROVIDER_URL = "http://localhost:8080/callback"
const val LOGIN_URL = "http://localhost:8080/login"
const val AUTHORIZE_URL = "https://accounts.google.com/o/oauth2/auth"
const val ACCESS_TOKEN_URL = "https://accounts.google.com/o/oauth2/token"
const val GOOGLE_HOST = "https://www.googleapis.com"
const val USER_INFO_SCOPE = "auth/userinfo.profile"
const val OAUTH2_USERINFO = "oauth2/v2/userinfo"


fun Application.configureAuthProvider(
    httpClient: HttpClient = applicationHttpClient,
    redirect: Redirect = Redirect(mutableMapOf())
) {
    install(Authentication) {
        oauth("auth-oauth-google") {
            urlProvider = { PROVIDER_URL }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = AUTHORIZE_URL,
                    accessTokenUrl = ACCESS_TOKEN_URL,
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("GOOGLE_CLIENT_ID"),
                    clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                    defaultScopes = listOf("$GOOGLE_HOST/$USER_INFO_SCOPE"),
                    extraAuthParameters = listOf("access_type" to "offline"),
                    onStateCreated = { call: ApplicationCall, state -> redirect[state] = call }
                )
            }
            client = httpClient
        }
    }

    routing {
        authenticate("auth-oauth-google") {
            get("/login") {
                // TODO: Test this side effect
                // Redirects to AUTHORIZE_URL with the OAuth2 parameters
            }
            get("/callback") {
                call.principal<OAuthAccessTokenResponse.OAuth2>()
                    ?.run { state?.let { UserSession(accessToken, it) } }
                    ?.let { userSession ->
                        // TODO: Test this side effect
                        call.sessions.set(userSession)

                        redirect[userSession.state]?.let {
                            // TODO: Test this side effect
                            call.respondRedirect(it)

                            // TODO: Test this early return
                            return@get
                        }
                    }

                // TODO: Test this side effect
                call.respondRedirect("/home")
            }
        }
        // TODO: Test if the root path is redirected to /login
        get("/") {
            call.respondRedirect("/login")
        }
        // TODO: Test if any path other than `/home` or `/` is redirected to /home
        get("/{path}") {
            call.respondRedirect("/home")
        }
        get("/home") {
            checkUserSession(call) {
                httpClient.get("$GOOGLE_HOST/$OAUTH2_USERINFO") {
                    bearerAuth(it.token)
                }.body<GoogleUserInfo>().let {
                    // TODO: Test the serialization of GoogleUserInfo
                    respondText(it.name)
                }
            }
        }
    }
}
