package Screens.Services


import DashboardPreview
import DashboardPreviewSmaller
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun CloudFunctions(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(37.dp)
    ) {
        DashboardPreview(DashboardType.Line, navController, "Cloud Functions Stats", false, "Desempenho nos últimos 15 dias")
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            DashboardPreviewSmaller(DashboardType.Pie, "Memória em uso")
            DashboardPreviewSmaller(DashboardType.Pie, "Tráfego")
        }
        DashboardPreview(DashboardType.Bar, navController, "Consumo", false, "Consumo GB/Mês")
        DashboardPreview(DashboardType.Line, navController, "Tráfego", false, "Número de usuários ativos por dia")
        Spacer(modifier = Modifier.size(24.dp))
    }
}

