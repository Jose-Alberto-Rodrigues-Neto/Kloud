package project.kloud.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptionHandler() {
    install(StatusPages) {
        unhandled { call ->
            call.respondText(
                text = "Internal server error",
                contentType = ContentType.Text.Plain,
                status = HttpStatusCode.InternalServerError
            )
        }

        exception<BadContentTypeFormatException> { call, cause ->
            call.respondText(
                text = cause.localizedMessage,
                contentType = ContentType.Text.Plain,
                status = HttpStatusCode.BadRequest
            )
        }
    }
}