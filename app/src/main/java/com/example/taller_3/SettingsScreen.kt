package com.example.taller_3

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavHostController, backgroundColor: MutableState<Color>) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val colors = listOf(Color.Red, Color.Blue, Color.Yellow, Color.Green)
    var selectedColor by remember { mutableStateOf(backgroundColor.value) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Selecciona el color de fondo")
                Spacer(modifier = Modifier.height(16.dp))
                colors.forEach { color ->
                    Button(
                        onClick = {
                            selectedColor = color
                            backgroundColor.value = color
                            editor.putInt("background_color", color.toArgb()).apply()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(color)
                    ) {
                        Text(text = " ", color = Color.Transparent)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("home") }) {
                    Text(text = "Volver a la pantalla principal")
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