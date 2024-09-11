@Preview
@Composable
fun Dashboard(){
    fun BarChart(
        barChartData = BarChartData(bars = listOf(Bar(label = "Bar Label", value = 100f, color = Color.Red)),
            // Optional properties.
            modifier = Modifier.fillMaxSize(),
            animation = simpleChartAnimation(),
            barDrawer = SimpleBarDrawer(),
            xAxisDrawer = SimpleXAxisDrawer(),
            yAxisDrawer = SimpleYAxisDrawer(),
            labelDrawer = SimpleValueDrawer()
        )
}