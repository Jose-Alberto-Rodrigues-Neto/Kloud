package project.kloud.outbound.adapters.metrics

import com.google.monitoring.v3.Point
import com.google.monitoring.v3.TimeInterval
import com.google.monitoring.v3.TypedValue
import io.ktor.server.config.*
import project.kloud.outbound.adapters.metrics.models.PongMetrics
import project.kloud.outbound.ports.configureMetrics

val ApplicationConfig.pongMetricsWriter
    get() = configureMetrics<PongMetrics>("pong", 5) { interval ->
        listOf(buildPoint(interval, ping))
    }

private inline fun <reified T> buildPoint(
    timeInterval: TimeInterval,
    pointValue: T,
) = Point.newBuilder()
    .apply {
        interval = timeInterval
        value = buildTypedValue(pointValue)
    }.build()

private inline fun <reified T> buildTypedValue(value: T) = TypedValue.newBuilder()
    .apply {
        when (value) {
            is Double -> doubleValue = value
            is Long -> int64Value = value
            is String -> stringValue = value
            else -> throw IllegalArgumentException("Unsupported value type")
        }
    }.build()

