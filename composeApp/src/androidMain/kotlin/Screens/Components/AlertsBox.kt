package Screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.kloud.R

data class AlertBoxProps(
    val alertName: String,
    val alertIcon: Int
)

@Composable
fun AlertsBox(props: AlertBoxProps){
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(59.dp)
            .background(Color.Gray, shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.CenterStart
    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .size(42.dp),
                painter = painterResource(props.alertIcon),
                contentDescription = props.alertName,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = props.alertName,
                fontSize = 20.sp,
                color = Color.White
            )

        }
    }
}

@Composable
fun AlertboxList(alertProps: List<AlertBoxProps>){
    Column(
        modifier = Modifier
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        alertProps.forEachIndexed { index, alertProps ->
            AlertsBox(alertProps)
        }
    }
}