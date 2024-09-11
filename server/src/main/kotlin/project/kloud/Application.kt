package project.kloud

import io.ktor.server.application.*
import io.ktor.server.routing.*
import project.kloud.outbound.adapters.metrics.pongMetricsWriter
import project.kloud.plugins.configureAppMetrics
import project.kloud.plugins.configureExceptionHandler
import project.kloud.plugins.configureSerialization
import project.kloud.plugins.configureSession
import project.kloud.routes.api

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.isAlive() {
    val pongMetrics = environment.config.config("ktor.metrics")
        .pongMetricsWriter

    configureExceptionHandler()
    configureSerialization()
    configureSession()
    configureAppMetrics(pongMetrics)

    routing {
        api()
    }
}