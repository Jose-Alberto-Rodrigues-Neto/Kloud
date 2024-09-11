package project.kloud.outbound.ports

import arrow.core.raise.result
import com.google.api.LabelDescriptor
import com.google.api.Metric
import com.google.api.MetricDescriptor
import com.google.api.MetricDescriptor.MetricKind
import com.google.api.MonitoredResource
import com.google.api.client.util.DateTime
import com.google.cloud.monitoring.v3.MetricServiceClient
import com.google.monitoring.v3.CreateTimeSeriesRequest
import com.google.monitoring.v3.Point
import com.google.monitoring.v3.TimeInterval
import com.google.monitoring.v3.TimeSeries
import com.google.protobuf.Timestamp
import io.ktor.server.config.*
import java.time.Instant
import java.util.*
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

interface MetricWriter<T: Map<String, String>> {
    val metricDescriptor: MetricDescriptor
    val monitoredResource: MonitoredResource

    fun writeMetric(metric: T, timestamp: Long): Result<Unit>
}

fun <T: Map<String, String>> ApplicationConfig.configureMetrics(
    name: String,
    bufferSize: Int,
    block: T.(TimeInterval) -> Iterable<Point>,
) = object : MetricWriter<T> {
        override val metricDescriptor = config("$name.descriptor")
            .buildMetricDescriptor()
        override val monitoredResource = config("$name.monitoredResource")
            .buildMonitoredResource()
        val timeSeriesConfig = config("$name.timeSeries")

        val metricsServiceClient = MetricServiceClient.create()

        val timeSeriesBuffer = mutableListOf<TimeSeries>()

        override fun writeMetric(metric: T, timestamp: Long): Result<Unit> = result {
            timestamp.
                let(::buildTimeInterval)
                .let { interval ->
                    buildTimeSeries(
                        metricData = metricDescriptor.buildMetric(metric),
                        pointsIter = metric.block(interval)
                    )
                }
                .let(timeSeriesBuffer::add)

            metricsServiceClient
                .takeIf { timeSeriesBuffer.size >= bufferSize }
                ?.use { client ->
                    timeSeriesConfig
                        .createTimeSeriesRequest(timeSeriesBuffer)
                        .let(client::createTimeSeries)

                    timeSeriesBuffer.clear()
                }
        }
    }

private fun ApplicationConfig.createTimeSeriesRequest(
    timeSeriesIter: Iterable<TimeSeries>,
) = CreateTimeSeriesRequest.newBuilder()
    .apply {
        name = property("name").getString()
        addAllTimeSeries(timeSeriesIter)
    }.build()

private fun buildTimeInterval(
    timestamp: Long
) = TimeInterval.newBuilder()
    .apply {

        endTime = Timestamp.newBuilder()
            .apply {
                seconds = timestamp.div(1000)
                nanos = (timestamp % 1000).toInt()
            }.build()
    }.build()

private fun ApplicationConfig.buildMetricDescriptor() = MetricDescriptor.newBuilder()
    .apply {
        type = "type"
            .let(::property).getString()

        metricKind = "kind"
            .let(::property).getString()
            .let(MetricKind::valueOf)

        description = "description"
            .let(::propertyOrNull)
            ?.getString()

        configList("labels")
            .map(::buildLabel)
            .let(::addAllLabels)
    }.build()

private fun buildLabel(prop: ApplicationConfig) = LabelDescriptor.newBuilder()
    .apply {
        key = "key"
            .let(prop::property)
            .getString()

        valueType = "type"
            .let(prop::property).getString()
            .let(LabelDescriptor.ValueType::valueOf)

        "description"
            .let(prop::propertyOrNull)
            ?.getString()
            ?.let(::setDescription)
    }.build()

private fun ApplicationConfig.buildMonitoredResource() = MonitoredResource.newBuilder()
    .apply {
        property("type").getString()
            .let(::setType)

        config("labels")
            .toMap().mapValues { (_, prop) ->
                if (prop !is String)
                    throw IllegalArgumentException("Label value must be a string")

                prop
            }.let(::putAllLabels)
    }.build()

private fun MetricWriter<*>.buildTimeSeries(
    metricData: Metric,
    pointsIter: Iterable<Point>,
) = TimeSeries.newBuilder()
    .apply {
        metric = metricData
        resource = monitoredResource

        addAllPoints(pointsIter)
    }.build()

private fun MetricDescriptor.buildMetric(labels: Map<String, String>) = Metric.newBuilder()
    .apply {
        type = this@buildMetric.type
        labelsList
            .associate { it.key to it.valueType }
            .mapValues { (key, _) ->
                val value = labels[key]

                requireNotNull(value) { "Label $key not found" }

                value
            }.let(::putAllLabels)
    }.build()