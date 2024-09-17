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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
    var item = remember { mutableIntStateOf(2) }
    Scaffold (
        bottomBar = {
            NavMenu(navController, item)
        }
    ){paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "Home"
        ){
            composable(route = "Home") {
                item.intValue = 2
                Home(navController)
            }
            composable (route = "Settings") {
                item.intValue = 0
                Settings()
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
                Services()
            }
        }
    }

}
