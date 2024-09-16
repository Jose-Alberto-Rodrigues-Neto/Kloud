package project.kloud

import App
import Dashboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ui.screens.DashboardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DashboardPreview()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Preview
@Composable
fun DashboardPreview() {
    val mockViewModel = DashboardViewModel()
    Dashboard(viewModel = mockViewModel)
}