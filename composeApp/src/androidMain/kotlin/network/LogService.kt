package network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://34.95.198.20:5432/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface LogService {
    @GET
    suspend fun getLogs(): String
}

object LogApi {
    val LogService: LogService by lazy {
        retrofit.create(LogService::class.java)
    }
}