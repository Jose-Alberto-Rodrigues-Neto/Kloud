package network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateJsonAdapter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

    @FromJson
    fun fromJson(json: String): Date? {
        return try {
            dateFormat.parse(json)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    fun toJson(date: Date?): String? {
        return date?.let { dateFormat.format(it) }
    }
}
