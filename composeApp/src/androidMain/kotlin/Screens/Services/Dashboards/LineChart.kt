package Screens.Services.Dashboards

import Screens.Components.parseDateToFloat
import Screens.Services.Dtos.CpuUsageResponse
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
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
fun LineChartComponent(cpuUsageData: State<List<CpuUsageResponse>>) {
    val lineChartData = cpuUsageData.value.firstOrNull()?.let { response ->
        LineChartData(
            points = response.points.map { point ->
                LineChartData.Point(
                    label = parseDateToFloat(point.interval.startTime).toString(),
                    value = point.value.toFloat()
                )
            },
            lineDrawer = SolidLineDrawer()
        ).let(::listOf)
    }?: (1..7).map { index -> LineChartData(
        points = (1..index).map { point ->
            LineChartData.Point(
                label = "label",
                value = point.toFloat()
            )
        },
        lineDrawer = SolidLineDrawer()
    ) }

    return LineChart(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxSize(),
        linesChartData = lineChartData,
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
