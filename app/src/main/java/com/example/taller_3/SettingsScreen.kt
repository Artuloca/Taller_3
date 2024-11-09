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
    val sharedPreferences = context.getSharedPreferences("com.example.taller_3.PREFERENCES", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

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
                Button(onClick = {
                    backgroundColor.value = Color.Red
                    editor.putInt("background_color", Color.Red.toArgb()).apply()
                }) {
                    Text(text = "Rojo")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    backgroundColor.value = Color.Blue
                    editor.putInt("background_color", Color.Blue.toArgb()).apply()
                }) {
                    Text(text = "Azul")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    backgroundColor.value = Color.Yellow
                    editor.putInt("background_color", Color.Yellow.toArgb()).apply()
                }) {
                    Text(text = "Amarillo")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    backgroundColor.value = Color.Green
                    editor.putInt("background_color", Color.Green.toArgb()).apply()
                }) {
                    Text(text = "Verde")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("home") }) {
                    Text(text = "Ir a Pantalla Principal")
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