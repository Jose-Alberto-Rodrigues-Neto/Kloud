package Screens

import Dashboard
import Navgation.NavMenu
import Screens.Components.BoxList
import Screens.Components.ServiceBox
import Screens.Components.ServiceBoxProps
import Screens.Components.TabTitle
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.kloud.R
import ui.screens.DashboardViewModel

fun onClick(){

}

val boxProps = listOf(
    ServiceBoxProps("Computer Engine", R.drawable.home, "Cloud Functions", onClick()),
    ServiceBoxProps("Cloud Storage", R.drawable.home, "", onClick()),
    ServiceBoxProps("Persistent Disk", R.drawable.home, "Home", onClick()),
    ServiceBoxProps("home", R.drawable.home, "Home", onClick())
)

@Composable
fun Home(){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TabTitle(
                title = "Dashboard",
                icon = R.drawable.keyboard_arrow_right,
                route = "Dashboard",
                onClick = onClick()
            )
            DashboardPreview()
            TabTitle(
                title = "Serviços",
                icon = R.drawable.keyboard_arrow_right,
                route = "Services",
                onClick = onClick()
            )
            BoxList(boxProps)

        }
}
@Preview
@Composable
fun DashboardPreview() {
    val mockViewModel = DashboardViewModel()
    Dashboard(viewModel = mockViewModel)
}
