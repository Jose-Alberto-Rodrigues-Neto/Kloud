package project.kloud

import Navgation.NavMenu
import Screens.Alerts
import Screens.Home
import Screens.Profile
import Screens.Services
import Screens.Settings
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppAndroidPreview()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            NavMenu(navController)
        }
    ){paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "Home"
        ){
            composable(route = "Home") { Home(navController) }
            composable (route = "Settings") { Settings() }
            composable (route = "Alerts") { Alerts() }
            composable (route = "Profile") { Profile() }
            composable (route = "Services") { Services() }
        }
    }

}
