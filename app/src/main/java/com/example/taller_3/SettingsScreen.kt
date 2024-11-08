package com.example.taller_3.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavHostController, backgroundColor: MutableState<Color>) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor.value)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Settings Screen")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("home") }) {
                    Text(text = "Go to Home")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { backgroundColor.value = Color.Red }) {
                    Text(text = "Rojo")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { backgroundColor.value = Color.Blue }) {
                    Text(text = "Azul")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { backgroundColor.value = Color.Yellow }) {
                    Text(text = "Amarillo")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { backgroundColor.value = Color.Green }) {
                    Text(text = "Verde")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val backgroundColor = remember { mutableStateOf(Color.White) }
    SettingsScreen(rememberNavController(), backgroundColor)
}