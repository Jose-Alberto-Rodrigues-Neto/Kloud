package project.kloud.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import project.kloud.domain.PING_PARAMETER_EXCEPTION_MESSAGE
import project.kloud.domain.Pong
import java.time.Instant

fun Route.getPing(deployedAt: Instant) =
    get("/is-alive") {
        call
        val plusOne = call.parameters["ping"]
            ?.toLongOrNull()
            ?.let { it + 1 }
        requireNotNull(plusOne) { PING_PARAMETER_EXCEPTION_MESSAGE }

        call.respond(HttpStatusCode.OK, Pong(deployedAt, plusOne))
    }

fun Route.getMetrics() =
    get("/metrics") {
        val path = call.request.uri.split("/")
            .dropWhile { it != "metrics" }.drop(1)

        // TODO: Get metrics from path
    }

fun Route.api() {
    val deployedAt = Instant.now()

    route("/v0") {
        getPing(deployedAt)
        getMetrics()
    }
}