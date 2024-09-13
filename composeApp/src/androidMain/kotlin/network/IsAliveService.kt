package network

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "url"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface IsAliveService {
    @GET("ping")
    suspend fun isAlive(): String
}

object IsAliveApi {
    val isAliveService : IsAliveService by lazy {
        retrofit.create(IsAliveService::class.java)
    }
}