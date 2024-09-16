package ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import network.IsAliveApi
import java.io.IOException

class DashboardViewModel : ViewModel() {

    var serverStatus: String by mutableStateOf("Status desconhecido")
        private set

    fun pingServer() {
        viewModelScope.launch {
            try {
                val response = IsAliveApi.isAliveService.isAlive(ping = 10)
                serverStatus = "Server está online: $response"
            } catch (e: IOException) {
                serverStatus = "Erro: ${e.message}"
            }
        }
    }
}