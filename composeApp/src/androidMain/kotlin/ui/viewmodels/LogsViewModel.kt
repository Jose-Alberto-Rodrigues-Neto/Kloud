package ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import network.LogApi
import java.io.IOException

class LogsViewModel: ViewModel() {

    var logs: String by mutableStateOf("Logs desconhecidos")

    fun logService() {
        viewModelScope.launch {
            try {
                val response = LogApi.LogService.getLogs()
                logs = response
            } catch (e: IOException) {
                logs = "Erro ao buscar logs: ${e.message}"
            }
        }
    }
}