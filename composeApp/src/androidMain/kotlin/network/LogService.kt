package network

import Screens.Services.Dtos.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://34.95.198.20:5432/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()) // Permite a conversão de classes Kotlin
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface LogService {
    @GET(".")
    suspend fun getLogs(): List<Log>
}

object LogApi {
    val logService: LogService by lazy {
        retrofit.create(LogService::class.java)
    }
}