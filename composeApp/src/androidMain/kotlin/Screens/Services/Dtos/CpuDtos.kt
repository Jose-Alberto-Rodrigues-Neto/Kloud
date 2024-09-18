package Screens.Services.Dtos

import com.squareup.moshi.Json

data class MetricResponse(
    @Json(name = "metric") val metric: Metric,
    @Json(name = "resource") val resource: Resource,
    @Json(name = "points") val points: List<Point>
)

data class Metric(
    @Json(name = "type") val type: String,
    @Json(name = "labels") val labels: MetricLabels
)

data class MetricLabels(
    @Json(name = "instance_name") val instanceName: String
)

data class Resource(
    @Json(name = "type") val type: String,
    @Json(name = "labels") val labels: ResourceLabels
)

data class ResourceLabels(
    @Json(name = "project_id") val projectId: String,
    @Json(name = "zone") val zone: String,
    @Json(name = "instance_id") val instanceId: String
)

data class Point(
    @Json(name = "interval") val interval: Interval,
    @Json(name = "value") val value: String
)

data class Interval(
    @Json(name = "startTime") val startTime: String,
    @Json(name = "endTime") val endTime: String
)
