package network

import Screens.Services.Dtos.MetricResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://34.95.132.224:5432/v0/" +
        "metrics?name=/instance/cpu/usage_time&columnWidth=3600&metricLabelName=instance_name&metricLabelValue=gke&startTimeDay=7"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface CpuUsageService {
    @GET("metrics")
    suspend fun getCpuUsage(
        @Query("name") name: String,
        @Query("columnWidth") columnWidth: Int,
        @Query("metricLabelName") metricLabelName: String,
        @Query("metricLabelValue") metricLabelValue: String,
        @Query("startTimeDay") startTimeDay: Int
    ) : MetricResponse
}

object CpuUsageApi {
    val cpuUsageService: CpuUsageService by lazy {
        retrofit.create(CpuUsageService::class.java)
    }
}