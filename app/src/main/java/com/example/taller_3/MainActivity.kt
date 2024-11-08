package com.example.taller_3

import android.content.Context
import android.content.SharedPreferences
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
import com.example.taller_3.ui.theme.Taller_3Theme

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("com.example.taller_3.PREFERENCES", Context.MODE_PRIVATE)
        setContent {
            Taller_3Theme {
                val navController = rememberNavController()
                val backgroundColor = remember { mutableStateOf(Color.White) }
                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController, backgroundColor) }
                    composable("main") { MainScreen(navController, backgroundColor, sharedPreferences) }
                    composable("settings") { SettingsScreen(navController, backgroundColor) }
                }
            }
        }
    }
}
