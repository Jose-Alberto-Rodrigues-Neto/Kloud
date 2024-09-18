package Screens.Components

import Screens.Services.Dtos.Point
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MetricTable(points: List<Point>) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Título da tabela
        Text(text = "Uso da CPU", style = MaterialTheme.typography.h6)

        // Cabeçalho da tabela
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Text(text = "Início", modifier = Modifier.weight(1f), style = MaterialTheme.typography.body2)
            Text(text = "Fim", modifier = Modifier.weight(1f), style = MaterialTheme.typography.body2)
            Text(text = "Uso (s)", modifier = Modifier.weight(1f), style = MaterialTheme.typography.body2)
        }

        // Linhas da tabela
        points.forEach { point ->
            Row(modifier = Modifier.padding(bottom = 4.dp)) {
                Text(text = point.interval.startTime, modifier = Modifier.weight(1f))
                Text(text = point.interval.endTime, modifier = Modifier.weight(1f))
                Text(text = point.value, modifier = Modifier.weight(1f))
            }
        }
    }
}