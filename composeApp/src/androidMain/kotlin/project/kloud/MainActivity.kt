package project.kloud

import App
import Navgation.NavMenu
import Screens.Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold (
                bottomBar = {
                    NavMenu()
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Home()
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    Scaffold (
        bottomBar = {
            NavMenu()
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Home()
        }
    }
}
