package project.kloud.outbound.ports

import com.google.api.LabelDescriptor
import com.google.api.Metric
import com.google.api.MetricDescriptor
import com.google.api.MetricDescriptor.MetricKind
import com.google.api.MonitoredResource
import com.google.cloud.monitoring.v3.MetricServiceClient
import com.google.monitoring.v3.CreateTimeSeriesRequest
import com.google.monitoring.v3.Point
import com.google.monitoring.v3.TimeInterval
import com.google.monitoring.v3.TimeSeries
import com.google.protobuf.Timestamp
import io.ktor.server.config.*

interface MetricWriter<T: Map<String, String>> {
    val metricDescriptor: MetricDescriptor
    val monitoredResource: MonitoredResource

    fun writeMetric(metric: T)
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

        override fun writeMetric(metric: T) {
            System.currentTimeMillis()
                .let(::buildTimeInterval)
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
            .mapValues { (key, type) ->
                val value = labels[key]

                requireNotNull(value) { "Label $key not found" }
                type.declaringJavaClass.isAssignableFrom(value::class.java)
                    .let { valueIsAssignableType ->
                        require(valueIsAssignableType) { "Label $key must be of type ${type.name}" }
                    }

                value
            }.let(::putAllLabels)
    }.build()