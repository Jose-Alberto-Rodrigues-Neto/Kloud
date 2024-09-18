package Screens.Services.Dashboards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import project.kloud.R

@Composable
fun PieChart() {
    val slices = listOf(
        PieChartData.Slice(
            value = 72f,
            color = androidx.compose.ui.res.colorResource(R.color.k_bright_blue)
        ),
        PieChartData.Slice(
            value = 28f,
            color = Color.Gray
        )
    )

    com.github.tehras.charts.piechart.PieChart(
        modifier = Modifier
            .size(120.dp)
            .padding(10.dp),
        pieChartData = PieChartData(
            slices = slices
        ),
        animation = simpleChartAnimation(),
        sliceDrawer = SimpleSliceDrawer(40f)
    )

}