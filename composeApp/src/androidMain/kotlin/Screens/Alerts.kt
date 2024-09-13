package Screens

import Navgation.NavMenu
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Alerts(){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Text(
            text = "Alerts",
            fontSize = 64.sp,
        )
    }
}