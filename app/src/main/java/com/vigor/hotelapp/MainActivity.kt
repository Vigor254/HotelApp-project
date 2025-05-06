package com.vigor.hotelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.vigor.hotelapp.navigation.AppNavHost
import com.vigor.hotelapp.ui.theme.HotellapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotellapTheme {
                MainApp()
            }
        }
    }

    @Composable
    fun MainApp() {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}