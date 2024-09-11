import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Preview
@Composable
fun Dashboard() {

    val bars = listOf(
        BarChartData.Bar(label = "Bar 1", value = 100f, color = Color.Red),
        BarChartData.Bar(label = "Bar 2", value = 150f, color = Color.Blue),
        BarChartData.Bar(label = "Bar 3", value = 80f, color = Color.Green),
        BarChartData.Bar(label = "Bar 4", value = 120f, color = Color.Yellow)
    )

    Column {
        NavBar(title = "Descrição de Métricas")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
            contentAlignment = Alignment.Center

        ) {
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
        }
    }
}