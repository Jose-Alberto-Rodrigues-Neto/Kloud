package project.kloud

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.isAlive() {
    routing {
        get("/is-alive") {
            call.apply {
                val plusOne = parameters["ping"]
                    ?.toLong()
                    ?.let { it + 1 }
                    ?: return@get respond(HttpStatusCode.BadRequest, "ping parameter is missing")


                return@get respondText("pong $plusOne")
            }
        }
    }
}