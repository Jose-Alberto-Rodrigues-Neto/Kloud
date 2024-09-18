package Screens.Services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import project.kloud.R
import ui.viewmodels.LogsViewModel

@Composable
fun Logs(viewModel: LogsViewModel, navController: NavController) {

    var logs by remember { mutableStateOf(viewModel.logs) }

    LaunchedEffect(viewModel.logs) {
        logs = viewModel.logs
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Logs do Cluster",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp) // Adiciona espaçamento abaixo do título
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Faz com que a LazyColumn ocupe o espaço restante
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(text = logs)
                }
            }

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .background(colorResource(R.color.k_blue), shape = RoundedCornerShape(16.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = { viewModel.logService() },
                    modifier = Modifier.background(color = Color.Transparent).padding(8.dp)
                        .width(370.dp)
                        .padding(6.dp)
                ) {
                    Text(text = "Verificar")
                }
            }
        }
    }
}
