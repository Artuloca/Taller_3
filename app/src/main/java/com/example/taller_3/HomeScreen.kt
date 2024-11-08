package com.example.taller_3

import android.os.AsyncTask
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

@Composable
fun HomeScreen(navController: NavHostController, backgroundColor: MutableState<Color>) {
    var greeting by remember { mutableStateOf("Loading...") }

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
                    Text(text = "Go to Main Screen")
                }
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val backgroundColor = remember { mutableStateOf(Color.White) }
    HomeScreen(rememberNavController(), backgroundColor)
}