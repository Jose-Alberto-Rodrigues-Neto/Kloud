package Screens.Components


import Screens.Services.Dtos.CpuUsageResponse
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

@Composable
fun CpuUsageDashboard(cpuUsageData: List<CpuUsageResponse>) {
    val lineChartData = cpuUsageData.map { response ->
        LineChartData(
            points = response.points.map { point ->
                LineChartData.Point(
                    label = parseDateToFloat(point.interval.startTime).toString(),
                    value = point.value.toFloat()
                )
            },
            lineDrawer = SolidLineDrawer()
        )
    }

    LineChart(
        linesChartData = lineChartData,
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(), // Ajuste conforme necessário
        yAxisDrawer = SimpleYAxisDrawer(), // Ajuste conforme necessário
        horizontalOffset = 5f
    )
}

fun parseDateToFloat(dateString: String): Float {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return try {
        val date: Date = format.parse(dateString) ?: return 0f
        date.time.toFloat() // Converte para timestamp em milissegundos e depois para float
    } catch (e: Exception) {
        0f
    }
}
