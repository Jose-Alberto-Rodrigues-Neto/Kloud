package ui.viewmodels

import Screens.Services.Dtos.LogEntry
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import network.LogApi
import java.io.IOException

class LogsViewModel: ViewModel() {

    var logs: String by mutableStateOf("Logs desconhecidos")
        private set

    fun logService() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    LogApi.logService.getLogs()
                }
                logs = response
            } catch (e: IOException) {
                logs = "Erro ao buscar logs ${e.message}"
            }
        }
    }
}