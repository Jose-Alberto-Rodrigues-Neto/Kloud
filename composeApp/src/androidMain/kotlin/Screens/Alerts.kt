package Screens

import Navgation.NavMenu
import Screens.Components.AlertBoxProps
import Screens.Components.AlertboxList
import Screens.Components.AlertsBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.kloud.R

@Composable
fun Alerts() {
    val alerts = listOf(
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
        AlertBoxProps(alertName = "Notificação!", alertIcon = R.drawable.warning),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.res.colorResource(R.color.k_blue), // Azul mais claro
                        androidx.compose.ui.res.colorResource(R.color.k_bright_blue)  // Azul mais escuro
                    )
                ),
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(2.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = "Alerts",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        AlertboxList(alerts)

        Spacer(modifier = Modifier.size(10.dp))
    }
}