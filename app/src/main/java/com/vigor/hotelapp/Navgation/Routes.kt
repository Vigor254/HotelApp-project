package com.vigor.hotelapp.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Home : Routes("home")
    object Details : Routes("details/{hotelId}") {
        fun createRoute(hotelId: Int) = "details/$hotelId"
    }
    object Booking : Routes("booking")
    object Confirmation : Routes("confirmation/{checkInDate}/{checkOutDate}/{guests}") {
        fun createRoute(checkInDate: String, checkOutDate: String, guests: String) =
            "confirmation/$checkInDate/$checkOutDate/$guests"
    }
    object Profile : Routes("profile")
}