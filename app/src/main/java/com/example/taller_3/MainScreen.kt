package com.example.taller_3

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun MainScreen(navController: NavHostController, backgroundColor: MutableState<Color>, sharedPreferences: SharedPreferences) {
    var name by remember { mutableStateOf("") }
    var namesList by remember { mutableStateOf(listOf<String>()) }
    var showList by remember { mutableStateOf(false) }

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
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Introduce tu nombre") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (name.isNotEmpty()) {
                        namesList = namesList + name
                        with(sharedPreferences.edit()) {
                            putString("saved_name", name)
                            apply()
                        }
                        name = ""
                    }
                }) {
                    Text(text = "Guarda el nombre")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showList = !showList }) {
                    Text(text = if (showList) "Esconder nombres" else "Mostrar nombres")
                }
                if (showList) {
                    LazyColumn {
                        items(namesList) { savedName ->
                            Text(text = savedName)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("settings") }) {
                    Text(text = "Ir a la pantalla de ajustes")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val backgroundColor = remember { mutableStateOf(Color.White) }
    MainScreen(rememberNavController(), backgroundColor, remember { mutableStateOf(null) as SharedPreferences })
}