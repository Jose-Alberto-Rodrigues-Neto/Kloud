package Screens

import Screens.Components.ServiceBar
import Screens.Components.ServiceBarList
import Screens.Components.ServiceBarProps
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import project.kloud.R

@Composable
fun Settings(navController: NavController) {
    val boxProps = listOf(
        ServiceBarProps(
            serviceName = "Cloud Storage",
            serviceIcon = R.drawable.cloud_storage,
            serviceRoute = "CloudStorage",
            navController = navController
        ),
        ServiceBarProps(
            serviceName = "Compute Engine",
            serviceIcon = R.drawable.compute_engine,
            serviceRoute = "ComputeEngine",
            navController = navController
        ),
        ServiceBarProps(
            serviceName = "Cloud Functions",
            serviceIcon = R.drawable.cloud_functions,
            serviceRoute = "CloudFunctions",
            navController = navController
        ),
        ServiceBarProps(
            serviceName = "Persistent Disk",
            serviceIcon = R.drawable.persistent_disk,
            serviceRoute = "PersistentDisk",
            navController = navController
        ),
        ServiceBarProps(
            serviceName = "Logs",
            serviceIcon = R.drawable.logs,
            serviceRoute = "Logs",
            navController = navController
        )
    )
    Column(
        modifier = Modifier
            .padding(vertical = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Services Settings",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Serviços utilizados pelo cliente",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        }

        ServiceBarList(boxProps)
    }
}