package Screens.Services



import Screens.Components.CpuUsageDashboard
import Screens.DashboardPreview
import Screens.DashboardPreviewSmaller
import Screens.DashboardType
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ui.viewmodels.CpuUsageViewModel


@Composable
fun ComputeEngine(navController: NavController) {
    //CPU usage vai ser colocado em algum lugar aqui
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(37.dp)
    ) {
        DashboardPreview(DashboardType.Line, navController, "Compute Engine Stats", false, "Desempenho nos últimos 15 dias")
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            DashboardPreviewSmaller(DashboardType.Pie, "Memória em uso")
            DashboardPreviewSmaller(DashboardType.Pie, "Tráfego")
        }
        DashboardPreview(DashboardType.Bar, navController, "Consumo", false, "Consumo GB/Mês", startTimeDat = 5, nthRowsPerDay = 4)
        DashboardPreview(DashboardType.Line, navController, "Tráfego", false, "Número de usuários ativos por dia", startTimeDat = 7, nthRowsPerDay = 2)
        DashboardPreview(DashboardType.Line, navController, "Uso da CPU", false, "Uso da CPU em em dias", startTimeDat = 7, nthRowsPerDay = 3)
        Spacer(modifier = Modifier.size(24.dp))
    }
}

