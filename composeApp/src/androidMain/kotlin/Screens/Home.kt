package Screens

import Screens.Components.BoxList
import Screens.Components.ServiceBoxProps
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import project.kloud.R


@Composable
fun Home(navController: NavController) {
    val boxProps = listOf(
        ServiceBoxProps("Cloud Storage", R.drawable.cloud_storage, "CloudStorage", navController),
        ServiceBoxProps("Compute Engine", R.drawable.compute_engine, "ComputeEngine", navController),
        ServiceBoxProps("Cloud Functions", R.drawable.cloud_functions, "CloudFunctions", navController),
        ServiceBoxProps("Persistent Disk", R.drawable.persistent_disk, "PersistentDisk", navController),
        ServiceBoxProps("Logs", R.drawable.persistent_disk, "Logs", navController)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(37.dp)
    ) {
        DashboardPreview(navController, "Dashboards", false)
        BoxList(boxProps, navController)
    }
}

