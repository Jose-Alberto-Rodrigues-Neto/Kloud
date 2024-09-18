package Screens.Services

import Screens.LogsDashboard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import project.kloud.R
import ui.viewmodels.LogsViewModel

@Composable
fun Logs(viewModel: LogsViewModel, navController: NavController) {

    var logs by remember { mutableStateOf(viewModel.logs) }

    LaunchedEffect(viewModel.logs) {
        logs = viewModel.logs
    }

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        Text(text = logs)

        Spacer(modifier = Modifier.size(16.dp))

        Box(
            modifier = Modifier.width(370.dp).background(
                colorResource(R.color.k_blue), shape = RoundedCornerShape(16.dp)
            ), contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { viewModel.logService() },
                modifier = Modifier.background(color = Color.Blue).padding(8.dp)
            ) {
                Text(text = "Logs do cluster")
            }
        }
    }

}
