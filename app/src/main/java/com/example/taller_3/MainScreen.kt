package com.example.taller_3

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, backgroundColor: MutableState<Color>, database: AppDatabase) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    var name by remember { mutableStateOf("") }
    var savedName by remember { mutableStateOf(sharedPreferences.getString("name", "") ?: "") }
    var namesList by remember { mutableStateOf(listOf<String>()) }
    var showList by remember { mutableStateOf(false) }
    val couroutineScope = rememberCoroutineScope()

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
                        editor.putString("name", name).apply()
                        savedName = name
                        name = ""
                    }
                }) {
                    Text(text = "Guarda en SharedPreferences")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Nombre guardado: $savedName")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    couroutineScope.launch {
                        if (name.isNotEmpty()) {
                            database.userDao().insert(User(1, name))
                            name = ""
                        }
                    }
                }) {
                    Text(text = "Guardar en SQLite")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    couroutineScope.launch {
                        val users = database.userDao().getUserById(1)
                        users?.let {
                            savedName = it.name
                        }
                    }

                }) {
                    Text(text = "Caragar desde SQLite")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showList = !showList }) {
                    Text(text = if (showList) "Esconder nombres" else "Mostrar nombres")
                }
                if (showList) {
                    LazyColumn {
                        items(namesList) { name ->
                            Text(text = name)
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
    MainScreen(rememberNavController(), backgroundColor, database = Room.databaseBuilder(LocalContext.current, AppDatabase::class.java, "app-database").build())
}