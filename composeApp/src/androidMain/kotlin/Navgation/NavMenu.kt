package Navgation

import androidx.compose.material.Badge
import androidx.compose.material.Text
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
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
fun NavMenu(navController: NavController, item: MutableIntState) {
    val navItens = listOf<NavItens>(
        NavItens(
            title = "Settings",
            icon = R.drawable.notifications,
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

    var selectedItem by item

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
