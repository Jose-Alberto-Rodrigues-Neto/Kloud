package Screens

import Screens.Components.TabTitle
import Screens.Services.Dashboards.LineChartComponent
import Screens.Services.Dashboards.PieChart
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import project.kloud.R


@Composable
fun Dashboard() {
    val bars = listOf(
        BarChartData.Bar(
            label = "Bar 1",
            value = 100f,
            color = androidx.compose.ui.res.colorResource(R.color.k_bright_blue)
        ),
        BarChartData.Bar(
            label = "Bar 2",
            value = 150f,
            color = androidx.compose.ui.res.colorResource(R.color.k_bright_blue)
        ),
        BarChartData.Bar(
            label = "Bar 3",
            value = 80f,
            color = androidx.compose.ui.res.colorResource(R.color.k_bright_blue),
        ),
        BarChartData.Bar(
            label = "Bar 4",
            value = 120f,
            color = androidx.compose.ui.res.colorResource(R.color.k_bright_blue)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            BarChart(
            modifier = Modifier
                .size(200.dp)
                .padding(20.dp),
            barChartData = BarChartData(
                bars = bars
            ),
            animation = simpleChartAnimation(),
            barDrawer = SimpleBarDrawer(),
            labelDrawer = SimpleValueDrawer(labelTextColor = Color.White),
            xAxisDrawer = SimpleXAxisDrawer(axisLineColor = Color.White),
            yAxisDrawer = SimpleYAxisDrawer(axisLineColor = Color.White, labelTextColor = Color.White, labelTextSize = 14.sp)
            )


        Spacer(modifier = Modifier.size(16.dp))

        Spacer(modifier = Modifier.size(16.dp))

    }
}

enum class DashboardType{
    Bar,
    Line,
    Pie
}
@Composable
fun DashboardPreview(dashboardType: DashboardType, navController: NavController, tabTitle: String, isClickable: Boolean, textStats: String) {
    //val mockViewModel = DashboardViewModel()
    Column(
        modifier = Modifier
            .height(290.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TabTitle(
            title = tabTitle,
            icon = R.drawable.keyboard_arrow_right,
            route = "Services",
            navController = navController,
            isClickable = isClickable
        )
        Box(
            modifier = Modifier
                .width(370.dp)
                .align(Alignment.CenterHorizontally)
                .background(
                    androidx.compose.ui.res.colorResource(R.color.k_blue),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Column {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = textStats,
                    textAlign = TextAlign.Start
                )
                when(dashboardType){
                DashboardType.Bar -> Dashboard()
                DashboardType.Line -> LineChartComponent()
                DashboardType.Pie -> PieChart()
                }
            }
        }
    }
}

@Composable
fun DashboardPreviewSmaller(dashboardType: DashboardType, text: String){
    //val mockViewModel = DashboardViewModel()
    Box(
            modifier = Modifier
                .width(180.dp)
                .height(160.dp)
                .background(
                    androidx.compose.ui.res.colorResource(R.color.k_blue),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ){
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier
                    .padding(4.dp),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                text = text,
                textAlign = TextAlign.Center
            )
            when(dashboardType){
                DashboardType.Bar -> Dashboard()
                DashboardType.Line -> LineChartComponent()
                DashboardType.Pie -> PieChart()
                }
        }
    }

}
