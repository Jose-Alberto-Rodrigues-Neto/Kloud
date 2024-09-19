package Screens.Services.Dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

data class Log(
    @Json(name = "type") val type: String?,
    @Json(name = "timestamp") val timestamp: String?,
    @Json(name = "data") val data: Any
)
