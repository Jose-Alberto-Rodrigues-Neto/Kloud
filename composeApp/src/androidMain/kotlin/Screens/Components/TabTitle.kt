package Screens.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TabTitleProps(
    val icon: Int,
    val route: String,
    val onClick: Unit
)

@Composable
fun TabTitle(
    title: String?,
    icon: Int,
    route: String,
    onClick: Unit
){
    Row(
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick },
        verticalAlignment = Alignment.CenterVertically
    ){
        title?.let {
            Text(
                text = it,
                fontSize = 32.sp
            )
        }
        Icon(
        painter = androidx.compose.ui.res.painterResource(icon),
        contentDescription = route,
        modifier = Modifier
            .size(32.dp)
    )
    }

}