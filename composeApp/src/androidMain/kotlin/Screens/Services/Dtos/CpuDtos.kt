package Screens.Services.Dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CpuUsageResponse(
    val metric: Metric,
    val resource: Resource,
    val points: List<Point>
)

@JsonClass(generateAdapter = true)
data class Metric(
    val type: String,
    val labels: Labels
)

@JsonClass(generateAdapter = true)
data class Labels(
    val instance_name: String
)

@JsonClass(generateAdapter = true)
data class Resource(
    val type: String,
    val labels: ResourceLabels
)

@JsonClass(generateAdapter = true)
data class ResourceLabels(
    val project_id: String,
    val zone: String,
    val instance_id: String
)

@JsonClass(generateAdapter = true)
data class Point(
    val interval: Interval,
    val value: String
)

@JsonClass(generateAdapter = true)
data class Interval(
    val startTime: String,
    val endTime: String
)
