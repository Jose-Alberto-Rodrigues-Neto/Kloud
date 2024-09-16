package ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import network.IsAliveApi
import java.io.IOException

class DashboardViewModel: ViewModel() {

    var serverStatus: String = "Status desconhecido"

    fun pingServer() {
        viewModelScope.launch {
            try {
                val result = IsAliveApi.isAliveService.isAlive()
                serverStatus = result
            } catch (e: IOException) {
                serverStatus = "Erro: ${e.message}"
            }
        }
    }
}