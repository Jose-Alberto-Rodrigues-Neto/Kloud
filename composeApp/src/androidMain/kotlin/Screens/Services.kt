package Screens

import Screens.Components.ServiceBarList
import Screens.Components.ServiceBarProps
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ui.viewmodels.IsAliveViewModel
import project.kloud.R


@Composable
fun Services(viewModel: IsAliveViewModel, navController: NavController) {
    val boxProps = listOf(
        ServiceBarProps(serviceName = "Cloud Storage", serviceIcon =  R.drawable.cloud_storage, serviceRoute = "CloudStorage", navController = navController),
        ServiceBarProps(serviceName = "Compute Engine", serviceIcon =  R.drawable.compute_engine, serviceRoute = "ComputeEngine", navController = navController),
        ServiceBarProps(serviceName = "Cloud Functions", serviceIcon =  R.drawable.cloud_functions, serviceRoute = "CloudFunctions", navController = navController),
        ServiceBarProps(serviceName = "Persistent Disk", serviceIcon =  R.drawable.persistent_disk, serviceRoute = "PersistentDisk", navController = navController),
        ServiceBarProps(serviceName = "Logs", serviceIcon =  R.drawable.logs, serviceRoute = "Logs", navController= navController)
    )
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
                text = "Serviços",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Espaço entre o título e o conteúdo principal
        Spacer(modifier = Modifier.size(20.dp)) // Ajuste o tamanho do espaço conforme necessário

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = serverStatus,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.size(4.dp))

                Button(
                    onClick = { viewModel.pingServer() },
                    modifier = Modifier.background(color = Color.Transparent).padding(8.dp)
                ) {
                    Text(text = "Verificar Status do Servidor", color = Color.White)
                }

                Spacer(modifier = Modifier.size(16.dp))

                ServiceBarList(boxProps)

            }
        }
    }
}
