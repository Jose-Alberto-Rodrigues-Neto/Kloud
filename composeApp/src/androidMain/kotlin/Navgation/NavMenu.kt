package Navgation

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kloud.composeapp.generated.resources.Res
import kloud.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun NavMenu(onClick:Unit) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Serviços", "Notificações", "Home", "Estatísticas", "Usuário")
    val icons = listOf(
        Icons.Filled.Settings,
        Icons.Filled.Notifications,
        Icons.Filled.Home,
        Icons.Filled.LocationOn,
        Icons.Filled.Person)
    NavigationBar(
        modifier = Modifier,
        containerColor = Color.Blue,
        contentColor = Color.Black

    ){
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(icons[index], item, tint = Color.White)

                },
                label = {
                    if(index == selectedItem){
                        Text(item, color = Color.White)
                    }
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClick
                }
            )
        }
    }

}


object Home