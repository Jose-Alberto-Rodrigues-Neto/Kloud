package network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://34.95.198.20:5432/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface LogService {
    @GET(".")
    suspend fun getLogs(): String
}

object LogApi {
    val logService: LogService by lazy {
        retrofit.create(LogService::class.java)
    }
}