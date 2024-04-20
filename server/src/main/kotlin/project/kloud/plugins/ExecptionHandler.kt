package project.kloud.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.util.reflect.*

fun Application.configureExceptionHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if (cause is BadContentTypeFormatException)
                return@exception call.respondText(
                    text = cause.localizedMessage,
                    contentType = ContentType.Text.Plain,
                    status = HttpStatusCode.BadRequest
                )

            call.respondText(
                text = "Internal server error",
                contentType = ContentType.Text.Plain,
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}