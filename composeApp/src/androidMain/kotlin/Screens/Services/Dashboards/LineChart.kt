package Screens.Services.Dashboards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import project.kloud.R

@Composable
fun LineChartComponent() {
    val points = listOf(
        LineChartData.Point(
            value = 50f,
            label = "dia 1"
        ),
        LineChartData.Point(
            value = 92f,
            label = "dia 2"
        ),
        LineChartData.Point(
            value = 80f,
            label = "dia 3"
        ),
        LineChartData.Point(
            value = 15f,
            label = "dia 4"
        ),
        LineChartData.Point(
            value = 42f,
            label = "dia 5",
        )
    )

    LineChart(
        modifier = Modifier
            .size(200.dp)
            .fillMaxSize(),
        linesChartData = listOf(LineChartData(points, lineDrawer = SolidLineDrawer())),
        pointDrawer = FilledCircularPointDrawer(
            color = androidx.compose.ui.res.colorResource(R.color.k_bright_blue)
        ),
        xAxisDrawer = SimpleXAxisDrawer(
            axisLineColor = Color.White,
            labelTextSize = 14.sp,
            labelTextColor = Color.White
        ),
        yAxisDrawer = SimpleYAxisDrawer(
            axisLineColor = Color.White,
            labelTextSize = 14.sp,
            labelTextColor = Color.White
        ),
        animation = simpleChartAnimation(),
    )
}
