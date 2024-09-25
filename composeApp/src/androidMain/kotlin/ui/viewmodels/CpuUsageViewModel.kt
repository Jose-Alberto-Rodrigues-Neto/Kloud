package ui.viewmodels

import Screens.Services.Dtos.CpuUsageResponse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.CpuUsageApi
import java.io.IOException

class CpuUsageViewModel(nthRowsPerDay: Int, startTimeDay: Int) : ViewModel() {
    init {
        viewModelScope.launch {
            val response: List<CpuUsageResponse> = CpuUsageApi.cpuUsageService.getCpuUsage(
                name = "/instance/cpu/usage_time",
                columnWidth = 86400 / nthRowsPerDay,
                metricLabelName = "instance_name",
                metricLabelValue = "gke",
                startTimeDay = startTimeDay
            )
            _usage.update { list -> response + list }
        }
    }

    // StateFlow para armazenar e observar a lista de dados de uso da CPU
    private val _usage = MutableStateFlow<List<CpuUsageResponse>>(emptyList())
    val usage: StateFlow<List<CpuUsageResponse>> get() = _usage

    // StateFlow para armazenar e observar o status do servidor
    private val _serverStatus = MutableStateFlow<String>("")
    val serverStatus: StateFlow<String> get() = _serverStatus

    // Função para buscar dados de uso da CPU
}

