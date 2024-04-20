package project.kloud

import Greeting
import SERVER_PORT
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
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