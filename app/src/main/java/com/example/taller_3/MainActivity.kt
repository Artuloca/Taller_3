package com.example.taller_3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taller_3.GreetingAsyncTask
import com.example.taller_3.SettingsScreen
import com.example.taller_3.ui.theme.Taller_3Theme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Taller_3Theme {
                val navController = rememberNavController()
                val backgroundColor = remember { mutableStateOf(Color.White) }
                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController, backgroundColor) }
                    composable("main") { MainScreen(navController, backgroundColor) }
                    composable("settings") { SettingsScreen(navController, backgroundColor) }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController, backgroundColor: MutableState<Color>) {
    var greeting by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        GreetingAsyncTask { result ->
            greeting = result
        }.execute()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = greeting)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("main") }) {
                    Text(text = "Ir a la Actividad Principal")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val backgroundColor = remember { mutableStateOf(Color.White) }
    Taller_3Theme {
        HomeScreen(rememberNavController(), backgroundColor)
    }
}
