package com.vigor.hotelapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vigor.hotelapp.model.Hotel // Add this import
import com.vigor.hotelapp.model.Booking // Ensure this import is present
import com.vigor.hotelapp.viewmodel.HotelViewModel

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: HotelViewModel = hiltViewModel()) {
    // Use proper type for state delegation
    val user by remember { mutableStateOf(viewModel.currentUser.value) }
    val bookings by remember { mutableStateOf(viewModel.bookings.value) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with Logout Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = {
                viewModel.logout()
                navController.navigate("home") {
                    popUpTo("profile") { inclusive = true }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Info
        Text(
            text = "Email: ${user?.id ?: "Not logged in"}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Admin Access (Only for admin user)
        if (user?.isAdmin == true) {
            Button(
                onClick = { navController.navigate("admin") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Admin Panel")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Bookings
        Text(
            text = "Your Bookings",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (bookings.isEmpty()) {
            Text(text = "No bookings yet", style = MaterialTheme.typography.bodyLarge)
        } else {
            bookings.forEach { booking ->
                BookingItem(booking = booking, viewModel = viewModel)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun BookingItem(booking: Booking, viewModel: HotelViewModel) {
    // Use proper type for hotel state
    var hotel by remember { mutableStateOf<Hotel?>(null) }

    LaunchedEffect(booking.hotelId) {
        hotel = viewModel.repository.getHotelById(booking.hotelId)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Hotel: ${hotel?.name ?: "Loading..."}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Hotel ID: ${booking.hotelId}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Hours: ${booking.hours}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Total Cost: $${booking.totalCost}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Booked On: ${booking.bookingDate}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}