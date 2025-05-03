package com.vigor.hotelapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vigor.hotelapp.ui.screens.*

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Routes.Details.route,
            arguments = listOf(navArgument("hotelId") { type = NavType.IntType })
        ) { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getInt("hotelId")
            HotelDetailScreen(
                hotelId = hotelId,
                navToBooking = { navController.navigate(Routes.Booking.route) }
            )
        }
        composable(Routes.Booking.route) {
            BookingScreen(navController = navController)
        }
        composable(
            route = Routes.Confirmation.route,
            arguments = listOf(
                navArgument("checkInDate") { type = NavType.StringType },
                navArgument("checkOutDate") { type = NavType.StringType },
                navArgument("guests") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ConfirmationScreen(
                checkInDate = backStackEntry.arguments?.getString("checkInDate") ?: "",
                checkOutDate = backStackEntry.arguments?.getString("checkOutDate") ?: "",
                guests = backStackEntry.arguments?.getString("guests") ?: "",
                onBackToHome = { navController.navigate(Routes.Home.route) }
            )
        }
        composable(Routes.Profile.route) {
            ProfileScreen(navController = navController)
        }
        }
    }
