package Screens.Components

import Screens.Services.Dtos.Point
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MetricTable(points: List<Point>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            // Título da tabela
            Text(text = "Uso da CPU", style = MaterialTheme.typography.h6)

            Spacer(modifier = Modifier.padding(16.dp))

            // Cabeçalho da tabela
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    text = "Início",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Fim",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Uso (s)",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.body2
                )
            }

            // Linhas da tabela
            points.forEach { point ->
                Row(modifier = Modifier.padding(bottom = 4.dp)) {
                    Text(text = formatDateTime(point.interval.startTime), modifier = Modifier.weight(1f))
                    Text(text = formatDateTime(point.interval.endTime), modifier = Modifier.weight(1f))
                    Text(text = formatValue(point.value), modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(dateTime: String): String {
    // Defina o formato de entrada (o formato da string original)
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    // Defina o formato de saída (o formato desejado)
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

    return try {
        // Converta a string para LocalDateTime e formate para a nova string
        val localDateTime = LocalDateTime.parse(dateTime, inputFormatter)
        localDateTime.format(outputFormatter)
    } catch (e: Exception) {
        // Em caso de erro, retorne a string original ou um valor padrão
        dateTime
    }
}

fun formatValue(value: String): String {
    return try {
        // Converta o valor para Double
        val doubleValue = value.toDouble()
        // Defina o formato para 2 casas decimais
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.format(doubleValue)
    } catch (e: NumberFormatException) {
        // Em caso de erro, retorne a string original ou um valor padrão
        value
    }
}

