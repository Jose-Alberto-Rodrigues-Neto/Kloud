package Navgation

import Screens.Alerts
import Screens.Home
import Screens.Profile
import Screens.Services
import Screens.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kloud.composeapp.generated.resources.Res
import kloud.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import project.kloud.R

data class NavItens(
    val title: String,
    val icon: Int,
    val hasNotification: Boolean,
    val badgedCount: Int? = null,
)

@Preview
@Composable
fun NavMenu(navController: NavController) {
    val navItens = listOf<NavItens>(
        NavItens(
            title = "Settings",
            icon = R.drawable.settings,
            hasNotification = false,
        ),
        NavItens(
            title = "Alerts",
            icon = R.drawable.notifications,
            hasNotification = true,
            badgedCount = 10,
        ),
        NavItens(
            title = "Home",
            icon = R.drawable.home,
            hasNotification = false,
        ),
        NavItens(
            title = "Services",
            icon = R.drawable.analytics,
            hasNotification = false,
        ),
        NavItens(
            title = "Profile",
            icon = R.drawable.profile_rounded,
            hasNotification = false,
        )
    )

    var selectedItem by remember { mutableIntStateOf(2) }

    NavigationBar(
        modifier = Modifier,
        containerColor = androidx.compose.ui.res.colorResource(R.color.k_blue),
    ) {
        navItens.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            if (!item.hasNotification) {
                                return@BadgedBox
                            }
                            Badge {
                                Text(item.badgedCount.toString())
                            }
                        }
                    ) {
                        Icon(
                            androidx.compose.ui.res.painterResource(item.icon),
                            item.title,
                        )
                    }
                },
                alwaysShowLabel = false,
                label = {
                    Text(item.title, color = Color.White)
                },
                selected = selectedItem == index,
                colors = NavigationBarItemColors(
                    Color.White,
                    Color.White,
                    androidx.compose.ui.res.colorResource(R.color.k_blue),
                    androidx.compose.ui.res.colorResource(R.color.k_darker_blue),
                    Color.White,
                    Color.White,
                    Color.White
                ),
                onClick = {
                    selectedItem = index
                    navController.navigate(route = item.title)
                }
            )
        }
    }
}
