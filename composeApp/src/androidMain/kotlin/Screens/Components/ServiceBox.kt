package Screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import project.kloud.R
import kotlin.math.round

data class ServiceBoxProps(
    val serviceTitle: String,
    val serviceImage: Int,
    val serviceRoute: String,
    val navController: NavController
)

@Composable
fun ServiceBox(
    props: ServiceBoxProps
) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .width(120.dp)
            .background(
                androidx.compose.ui.res.colorResource(R.color.k_blue),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { props.navController.navigate(route = props.serviceRoute) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.End)
                    .size(42.dp),
                painter = painterResource(props.serviceImage),
                contentDescription = props.serviceTitle,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start),
                text = props.serviceTitle,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun BoxList(props: List<ServiceBoxProps>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TabTitle(
            title = "Serviços",
            icon = R.drawable.keyboard_arrow_right,
            route = "Services",
            navController = navController
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            props.forEachIndexed { index, serviceBoxProps ->
                ServiceBox(
                    props = serviceBoxProps,
                )
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                )
            }
        }
    }
}