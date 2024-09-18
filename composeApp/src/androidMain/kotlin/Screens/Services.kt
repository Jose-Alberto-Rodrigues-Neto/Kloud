package Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.viewmodels.IsAliveViewModel

@Composable
fun Services(viewModel: IsAliveViewModel) {
    var serverStatus by remember { mutableStateOf(viewModel.serverStatus) }

    LaunchedEffect(viewModel.serverStatus) {
        serverStatus = viewModel.serverStatus
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Adicione uma cor de fundo para melhor visualização
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent) // Ajuste o fundo do título
                .padding(start = 16.dp, top = 16.dp)
        ) {
            Text(
                text = "Services",
                fontSize = 50.sp,
                color = Color.Blue
            )
        }

        // Espaço entre o título e o conteúdo principal
        Spacer(modifier = Modifier.size(80.dp)) // Ajuste o tamanho do espaço conforme necessário

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = serverStatus,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.size(16.dp))

                Button(
                    onClick = { viewModel.pingServer() },
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .padding(8.dp)
                ) {
                    Text(text = "Verificar Status do Servidor", color = Color.White)
                }
            }
        }
    }
}
