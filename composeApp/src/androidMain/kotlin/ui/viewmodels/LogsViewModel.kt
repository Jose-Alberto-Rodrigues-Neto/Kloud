package ui.viewmodels

import Screens.Services.Dtos.Log
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

    var logs: List<Log> by mutableStateOf(emptyList())
        private set

    var errorMessage: String? by mutableStateOf(null)

    fun logService() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    LogApi.logService.getLogs()
                }
                logs = response
                errorMessage = null
            } catch (e: IOException) {
                errorMessage = "Erro ao buscar logs: ${e.message}"
            }
        }
    }
}