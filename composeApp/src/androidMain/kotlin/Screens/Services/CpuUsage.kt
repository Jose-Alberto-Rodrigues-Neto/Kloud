package Screens.Services

import Screens.Components.MetricTable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ui.viewmodels.CpuUsageViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CpuUsage(viewModel: CpuUsageViewModel, navController: NavController) {

    var usage by remember { mutableStateOf(viewModel.usage) }

    LaunchedEffect(viewModel.usage) {
        usage = viewModel.usage
        viewModel.fetchCpuUsage()
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (usage.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    usage.let { data ->
        MetricTable(points = data.flatMap { it.points })
    }
}
