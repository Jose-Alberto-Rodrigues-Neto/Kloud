package Screens.Services

import Screens.Components.MetricTable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.viewmodels.CpuUsageViewModel

@Composable
fun CpuUsage(viewModel: CpuUsageViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchCpuUsage()
    }

    val usage by remember { mutableStateOf(viewModel.usage) }

    if (usage == null) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {
        MetricTable(points = usage!!.points)
    }
}