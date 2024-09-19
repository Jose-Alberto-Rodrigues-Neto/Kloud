package Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import project.kloud.R

@Composable
fun Login(navController: NavController) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            androidx.compose.ui.res.colorResource(R.color.k_bright_blue), // Azul mais claro
                            androidx.compose.ui.res.colorResource(R.color.k_blue)  // Azul mais escuro
                        )
                    )
                )
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Logo centralizado
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                   Image(
                        painter = painterResource(id = R.drawable.kloud_logo), // Altere para seu recurso de imagem PNG
                        contentDescription = null,
                        modifier = Modifier
                            .size(380.dp) // Tamanho ajustado da imagem
                    )
                    Image(
                        painter = painterResource(id = R.drawable.kloud_key), // Altere para seu recurso de imagem PNG
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .size(180.dp) // Tamanho ajustado da imagem
                    )
                }

                // Botão "Entrar com o Google"
                Button(
                    onClick = { navController.navigate("Home") },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.8f) // Largura do botão 80% da tela
                        .height(60.dp) // Altura do botão
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google), // Imagem PNG do Google
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Entre com o Google")
                    }
                }
            }
        }
    }


