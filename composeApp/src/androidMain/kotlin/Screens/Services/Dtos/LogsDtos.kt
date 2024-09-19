package Screens.Services.Dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class LogEntry(
    val type: String,
    val timestamp: Date,
    val data: Any?
)

@JsonClass(generateAdapter = true)
sealed class LogDataWrapper {
    @JsonClass(generateAdapter = true)
    data class JsonDataWrapper(
        val data: JsonData
    ) : LogDataWrapper()

    @JsonClass(generateAdapter = true)
    data class ProtoDataWrapper(
        val data: ProtoData
    ) : LogDataWrapper()

    @JsonClass(generateAdapter = true)
    data class StringWrapper(
        val data: String
    ) : LogDataWrapper()
}

@JsonClass(generateAdapter = true)
data class LogData(
    val type: String, // Adicione um tipo de identificador para os diferentes tipos de dados, se necessário
    val jsonData: JsonData? = null,
    val protoData: ProtoData? = null
)

// Estrutura para dados de tipo JSON
@JsonClass(generateAdapter = true)
data class JsonData(
    val severity: String,
    val time: Date,
    val httpRequest: HttpRequest,
    @Json(name = "logging.googleapis.com/insertId")
    val insertId: String,
    @Json(name = "logging.googleapis.com/labels")
    val labels: Labels,
    @Json(name = "logging.googleapis.com/trace_sampled")
    val traceSampled: Boolean,
    @Json(name = "google.monitoring.v3.UptimeCheckConfig")
    val uptimeCheckConfig: UptimeCheckConfig,
    @Json(name = "google.monitoring.v3.UptimeCheckResult")
    val uptimeCheckResult: UptimeCheckResult
)

@JsonClass(generateAdapter = true)
data class ProtoData(
    val type_url: String,
    val value: String
)

@JsonClass(generateAdapter = true)
data class HttpRequest(
    val requestMethod: String,
    val requestUrl: String,
    val status: Int,
    val cacheLookup: Boolean,
    val cacheHit: Boolean,
    val cacheValidatedWithOriginServer: Boolean,
    val latency: Latency
)

@JsonClass(generateAdapter = true)
data class Latency(
    val seconds: Long,
    val nanos: Int
)

@JsonClass(generateAdapter = true)
data class LogLabels(
    @Json(name = "check_id")
    val checkId: String,
    @Json(name = "uptime_result_type")
    val uptimeResultType: String,
    @Json(name = "checker_location")
    val checkerLocation: String
)

@JsonClass(generateAdapter = true)
data class UptimeCheckConfig(
    @Json(name = "validate_ssl")
    val validateSsl: Boolean,
    val path: String,
    val timeout: Double,
    @Json(name = "mask_headers")
    val maskHeaders: Boolean,
    val period: Double,
    @Json(name = "content_matcher")
    val contentMatcher: String,
    @Json(name = "request_headers")
    val requestHeaders: Map<String, String>,
    @Json(name = "acceptable_response_codes")
    val acceptableResponseCodes: List<String>
)

@JsonClass(generateAdapter = true)
data class UptimeCheckResult(
    @Json(name = "content_mismatch")
    val contentMismatch: Boolean,
    @Json(name = "error_code")
    val errorCode: String
)
