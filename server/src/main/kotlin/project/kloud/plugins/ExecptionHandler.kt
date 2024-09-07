package project.kloud.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptionHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is IllegalArgumentException -> call.respond(HttpStatusCode.BadRequest, cause.message ?: "")
                else -> call.respond(HttpStatusCode.InternalServerError, cause.message ?: "")
            }
        }
    }
}