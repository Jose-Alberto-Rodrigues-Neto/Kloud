package ui.viewmodels

import Screens.Services.Dtos.CpuUsageResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import network.CpuUsageApi
import java.io.IOException

class CpuUsageViewModel : ViewModel() {

    var usage: List<CpuUsageResponse> by mutableStateOf(emptyList())
        private set

    var serverStatus: String by mutableStateOf("")

    fun fetchCpuUsage() {
        viewModelScope.launch {
            try {
                val response = CpuUsageApi.cpuUsageService.getCpuUsage(
                    name = "/instance/cpu/usage_time",
                    columnWidth = 86400,
                    metricLabelName = "instance_name",
                    metricLabelValue = "gke",
                    startTimeDay = 7
                )
                usage = response
            } catch (e: IOException) {
                serverStatus = "Erro: ${e.message}"
            }
        }
    }
}
