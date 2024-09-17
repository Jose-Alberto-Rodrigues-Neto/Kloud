package Screens

import DashboardPreview
import Screens.Components.BoxList
import Screens.Components.ServiceBoxProps
import Screens.Components.TabTitle
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
fun Home(navController: NavController){
    val boxProps = listOf(
    ServiceBoxProps("Computer Engine", R.drawable.home, "Cloud Functions", navController),
    ServiceBoxProps("Cloud Storage", R.drawable.home, "", navController),
    ServiceBoxProps("Persistent Disk", R.drawable.home, "Home", navController),
    ServiceBoxProps("home", R.drawable.home, "Home", navController)
)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(37.dp)
        ) {
            DashboardPreview(navController = navController)
            BoxList(boxProps, navController)
        }
}

