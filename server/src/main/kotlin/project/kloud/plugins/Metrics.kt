package project.kloud.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level
import project.kloud.outbound.adapters.metrics.models.PongMetrics
import project.kloud.outbound.ports.MetricWriter

fun Application.configureAppMetrics(metricWriter: MetricWriter<PongMetrics>) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/v0/is-alive") }
        format { call ->
            val status = call.response.status()
            val ping = call.parameters["ping"]?.toLongOrNull() ?: 0

            PongMetrics(
                status = status?.value,
                ping = ping
            ).let(metricWriter::writeMetric)

            "Status: $status Ping: $ping"
        }
    }
}