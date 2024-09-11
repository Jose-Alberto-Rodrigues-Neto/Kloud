package project.kloud.outbound.adapters.metrics.models

data class PongMetrics(
    val status: Int?,
    val ping: Long,
) : Map<String, String> by mapOf(
    "status" to status.toString(),
    "ping" to ping.toString()
)
