package com.example.mathapp.ui.Screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") {
            SplashScreen(navController = navController)
        }
        composable("LogInScreen") {
            LogInScreen(navController = navController)
        }

        composable("SignUpScreen") {
            SignUpScreen(navController = navController)
        }

    }
}