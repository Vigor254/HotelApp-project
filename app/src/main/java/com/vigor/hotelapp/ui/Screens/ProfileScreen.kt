package com.vigor.hotelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vigor.hotelapp.data.local.BookingEntity
import com.vigor.hotelapp.viewmodel.BookingViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: BookingViewModel = viewModel()
) {
    val bookings by viewModel.bookings.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchBookings()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Bookings",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (bookings.isEmpty()) {
            Text(
                text = "No bookings found.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn {
                items(bookings) { booking ->
                    BookingItem(booking = booking)
                }
            }
        }
    }
}

@Composable
fun BookingItem(booking: BookingEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Check-in: ${booking.checkIn}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Check-out: ${booking.checkOut}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Guests: ${booking.guests}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPrev(){
    ProfileScreen(rememberNavController())
}