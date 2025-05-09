package com.vigor.hotelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.vigor.hotelapp.navigation.AppNavHost
import com.vigor.hotelapp.ui.theme.HotelAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotelAppTheme {
                HotelApp()
            }
        }
    }

    @Composable
    fun HotelApp() {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}