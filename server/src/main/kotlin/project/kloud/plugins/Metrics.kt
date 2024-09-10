package project.kloud.plugins

import com.google.api.Metric
import com.google.api.MonitoredResource
import com.google.cloud.monitoring.v3.MetricServiceClient
import com.google.monitoring.v3.*
import com.google.protobuf.Timestamp
import io.ktor.server.application.*

fun Application.setupGoogleCloudMetrics() {
    val points = buildTimeInterval()
        .let { baseInterval ->
            val points = listOf(buildPoint<Double>(baseInterval, 0.0))

            (1..10).fold(points) { acc: List<Point>, _ ->
                val interval = buildTimeInterval()
                val value = (interval.startTime.seconds - baseInterval.startTime.seconds) / 1000.0

                acc + buildPoint<Double>(interval, value)
            }
        }

    metricsServiceClient.use { client ->
        createTimeSeriesRequest(
            timeSeriesName = "ktor.metrics.googleCloudMetrics.timeSeries.name"
                .let(environment.config::property).getString(),
            timeSeriesIter = listOf(
                buildTimeSeries(
                    metricData = buildMetric(mapOf()),
                    monitoredResource = buildMonitoredResource(),
                    pointsIter = points
                )
            )
        ).let(client::createTimeSeries)
    }
}

private val Application.metricsServiceClient by lazy { MetricServiceClient.create() }

private fun Application.createTimeSeriesRequest(
    timeSeriesName: String,
    timeSeriesIter: Iterable<TimeSeries>,
) = CreateTimeSeriesRequest.newBuilder()
    .apply {
        name = timeSeriesName
        addAllTimeSeries(timeSeriesIter)
    }.build()

private fun Application.buildTimeSeries(
    metricData: Metric,
    monitoredResource: MonitoredResource,
    pointsIter: Iterable<Point>,
) = TimeSeries.newBuilder()
    .apply {
        metric = metricData
        resource = monitoredResource

        addAllPoints(pointsIter)
    }.build()

private fun Application.buildTimeInterval(
    currentMillis: Long = System.currentTimeMillis(),
) = TimeInterval.newBuilder()
    .apply {
        Timestamp.newBuilder()
            .apply {
                seconds = currentMillis / 1000
                nanos = ((currentMillis % 1000) * 1000000).toInt()
            }
            .build()
            .let(::setEndTime)
    }.build()

private fun Application.buildMonitoredResource() = MonitoredResource.newBuilder()
    .apply {
        "ktor.metrics.googleCloudMetrics.monitoredResource.type"
            .let(environment.config::property).getString()
            .let(::setType)

        "ktor.metrics.googleCloudMetrics.monitoredResource.labels"
            .let(environment.config::config)
            .toMap().mapValues { (_, prop) ->
                if (prop !is String)
                    throw IllegalArgumentException("Label value must be a string")

                prop
            }
            .let(::putAllLabels)
    }.build()

private fun Application.buildMetric(labels: Map<String, String>) = Metric.newBuilder()
    .apply {
        "ktor.metrics.googleCloudMetrics.metric.type"
            .let(environment.config::property).getString()
            .let(::setType)

        putAllLabels(labels)
    }.build()

private inline fun <reified T> Application.buildPoint(
    timeInterval: TimeInterval,
    pointValue: T,
) = Point.newBuilder()
    .apply {
        interval = timeInterval
        value = buildTypedValue(pointValue)
    }.build()

private inline fun <reified T> Application.buildTypedValue(value: T) = TypedValue.newBuilder()
    .apply {
        when (value) {
            is Double -> doubleValue = value
            is Long -> int64Value = value
            is String -> stringValue = value
            else -> throw IllegalArgumentException("Unsupported value type")
        }
    }.build()
