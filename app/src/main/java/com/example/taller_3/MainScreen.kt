package com.example.taller_3

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, backgroundColor: MutableState<Color>, sharedPreferences: SharedPreferences) {
    val context = LocalContext.current
    val userRepository = UserRepository(context)
    var name by remember { mutableStateOf("") }
    var savedName by remember { mutableStateOf(sharedPreferences.getString("name", "") ?: "") }
    var namesList by remember { mutableStateOf(userRepository.getAllUsers()) }
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
                        sharedPreferences.edit().putString("name", name).apply()
                        savedName = name
                        name = ""
                    }
                }) {
                    Text(text = "Guardar en SharedPreferences")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Nombre guardado: $savedName")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (name.isNotEmpty()) {
                        userRepository.insertUser(name)
                        namesList = userRepository.getAllUsers()
                        name = ""
                    }
                }) {
                    Text(text = "Guardar en SQLite")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showList = !showList }) {
                    Text(text = if (showList) "Esconder nombres" else "Mostrar nombres")
                }
                if (showList) {
                    LazyColumn {
                        items(namesList) { user ->
                            Text(text = user.name)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("settings") }) {
                    Text(text = "Ir a Configuraciones")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val backgroundColor = remember { mutableStateOf(Color.White) }
    MainScreen(rememberNavController(), backgroundColor, LocalContext.current.getSharedPreferences("com.example.taller_3.PREFERENCES", Context.MODE_PRIVATE))
}