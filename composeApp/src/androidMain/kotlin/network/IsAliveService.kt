package network

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Query

private const val BASE_URL = "http://34.95.198.20:5432/v0/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface IsAliveService {
    @GET("is-alive")
    suspend fun isAlive(@Query("ping") ping: Int): String
}

object IsAliveApi {
    val isAliveService: IsAliveService by lazy {
        retrofit.create(IsAliveService::class.java)
    }
}