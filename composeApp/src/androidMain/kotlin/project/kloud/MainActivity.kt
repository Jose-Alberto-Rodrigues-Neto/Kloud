package project.kloud

import Navgation.NavMenu
import Navgation.TopAppBar
import Screens.Alerts
import Screens.Home
import Screens.Login
import Screens.Profile
import Screens.Services
import Screens.Services.CloudFunctions
import Screens.Services.CloudStorage
import Screens.Services.ComputeEngine
import Screens.Services.Logs
import Screens.Services.PersistentDisk
import Screens.Settings
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.viewmodels.CpuUsageViewModel
import ui.viewmodels.IsAliveViewModel
import ui.viewmodels.LogsViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppAndroidPreview()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AppAndroidPreview() {
    val navController = rememberNavController()
    val item = remember { mutableIntStateOf(2) }
    val page = remember { mutableStateOf(false) }
    val mockLogsViewModel = LogsViewModel()
    val mockIsAliveViewModel = IsAliveViewModel()
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            page.value = destination.route != "Login"
        }
    }
    Scaffold (
        topBar = {
            if (page.value) {
                TopAppBar(navController)
            }
        },
        bottomBar = {
            if (page.value) {
                NavMenu(navController, item)
            }
        }
    ){paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "Login"
        ){
            composable(route = "Login") {
                Login(navController)
            }
            composable(route = "Settings") {
                item.intValue = 0
                Settings(navController)
            }
            composable(route = "Home") {
                item.intValue = 2
                Home(navController)
            }
            composable (route = "Alerts") {
                item.intValue = 1
                Alerts()
            }
            composable (route = "Profile") {
                item.intValue = 4
                Profile()
            }
            composable (route = "Services") {
                item.intValue = 3
                Services(mockIsAliveViewModel, navController)
            }
            composable(route = "CloudStorage") {
                item.intValue = 3
                CloudStorage(navController)
            }
            composable(route = "CloudFunctions"){
                item.intValue = 3
                CloudFunctions(navController)
            }
            composable(route = "ComputeEngine"){
                item.intValue = 3
                ComputeEngine(navController)
            }
            composable(route = "PersistentDisk"){
                item.intValue = 3
                PersistentDisk(navController)
            }
            composable(route = "Logs"){
                item.intValue = 3
                Logs(viewModel = mockLogsViewModel,navController)
            }
        }
    }

}
