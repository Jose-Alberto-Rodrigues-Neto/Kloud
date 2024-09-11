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
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Preview
@Composable
fun Dashboard(){
    BarChart(barChartData = BarChartData(
        bars = listOf(BarChartData.Bar(
            label = "Bar Chart", value = 100f,color = Color.Red)
        )
    ),
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        barDrawer = SimpleBarDrawer(),
        labelDrawer = SimpleValueDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer()
        )
}