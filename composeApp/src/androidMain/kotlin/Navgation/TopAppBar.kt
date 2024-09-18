package Navgation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.kloud.R


@Composable
fun TopAppBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(androidx.compose.ui.res.colorResource(R.color.k_blue))
            .safeContentPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "Admin",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = "Pedro Vinicius",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .size(50.dp)
                .clickable { navController.navigate("Alerts") }

        ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier.size(42.dp).align(Alignment.Center).clip(CircleShape)
                    .background(androidx.compose.ui.res.colorResource(R.color.k_darker_blue)),
                tint = Color.White
            )
            Icon(
                androidx.compose.ui.res.painterResource(R.drawable.notifications),
                contentDescription = null,
                modifier = Modifier.size(15.dp).align(Alignment.TopStart),
                tint = Color.White
            )
        }
    }
}