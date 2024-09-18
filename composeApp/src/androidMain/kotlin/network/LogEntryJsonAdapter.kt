package network

import Screens.Services.Dtos.JsonData
import Screens.Services.Dtos.LogEntry
import Screens.Services.Dtos.ProtoData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.text.SimpleDateFormat
import java.util.*

class LogEntryJsonAdapter(private val moshi: Moshi) : JsonAdapter<LogEntry>() {
    private val jsonDataAdapter = moshi.adapter(JsonData::class.java)
    private val protoDataAdapter = moshi.adapter(ProtoData::class.java)

    override fun fromJson(reader: JsonReader): LogEntry? {
        var type: String? = null
        var timestamp: Date? = null
        var data: Any? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "type" -> type = reader.nextString()
                "timestamp" -> timestamp = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).parse(reader.nextString())
                "data" -> {
                    when (reader.peek()) {
                        JsonReader.Token.BEGIN_OBJECT -> {
                            val dataObject = reader.peekJson()
                            val jsonData = jsonDataAdapter.fromJson(dataObject)
                            if (jsonData != null) {
                                data = jsonData
                            } else {
                                val protoData = protoDataAdapter.fromJson(dataObject)
                                if (protoData != null) {
                                    data = protoData
                                }
                            }
                        }
                        JsonReader.Token.STRING -> data = reader.nextString()
                        else -> reader.skipValue()
                    }
                }
                else -> reader.skipValue()
            }
        }
        reader.endObject()

        return if (type != null && timestamp != null && data != null) {
            LogEntry(type, timestamp, data)
        } else {
            null
        }
    }

    override fun toJson(writer: JsonWriter, value: LogEntry?) {
        // Implement serialization if needed
    }
}
