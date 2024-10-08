package project.kloud

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import project.kloud.plugins.configureExceptionHandler

const val PING_PARAMETER_EXCEPTION_MESSAGE = "The ping parameter must be a number"

fun Application.isAlive() {
    configureExceptionHandler()

    routing {
        get("/is-alive") {
            call.apply {
                val plusOne = parameters["ping"]
                    ?.toLongOrNull()
                    ?.let { it + 1 }
                    ?: throw BadContentTypeFormatException(PING_PARAMETER_EXCEPTION_MESSAGE)


                return@get respondText("pong $plusOne")
            }
        }
    }
}