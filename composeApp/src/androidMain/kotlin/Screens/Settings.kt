package Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Settings(){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Text(
            text = "Settings",
            fontSize = 64.sp,
        )
    }
}