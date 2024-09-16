import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import ui.screens.DashboardViewModel

@Composable
fun Dashboard(viewModel: DashboardViewModel) {

    var serverStatus by remember { mutableStateOf(viewModel.serverStatus) }

    LaunchedEffect(viewModel.serverStatus) {
        serverStatus = viewModel.serverStatus
    }

    val bars = listOf(
        BarChartData.Bar(label = "Bar 1", value = 100f, color = Color.Red),
        BarChartData.Bar(label = "Bar 2", value = 150f, color = Color.Blue),
        BarChartData.Bar(label = "Bar 3", value = 80f, color = Color.Green),
        BarChartData.Bar(label = "Bar 4", value = 120f, color = Color.Yellow)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // NavBar na parte superior
        NavBar(title = "Descrição de Métricas")

        // Espaçamento entre a NavBar e o gráfico
        Spacer(modifier = Modifier.weight(1f))

        // Gráfico no centro da tela
        BarChart(
            barChartData = BarChartData(
                bars = bars
            ),
            modifier = Modifier.size(300.dp),
            animation = simpleChartAnimation(),
            barDrawer = SimpleBarDrawer(),
            labelDrawer = SimpleValueDrawer(),
            xAxisDrawer = SimpleXAxisDrawer(),
            yAxisDrawer = SimpleYAxisDrawer()
        )

        Spacer(modifier = Modifier.size(16.dp))

        // Status do servidor
        Text(text = serverStatus)

        Spacer(modifier = Modifier.size(16.dp))

        // Botão para verificar status do servidor
        Button(
            onClick = { viewModel.pingServer() },
            modifier = Modifier
                .background(color = Color.Blue)
                .padding(8.dp)
        ) {
            Text(text = "Verificar Status do Servidor")
        }

        // Espaçamento na parte inferior para centralizar o gráfico
        Spacer(modifier = Modifier.weight(1f))
    }
}
