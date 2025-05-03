package com.vigor.hotelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmationScreen(
    checkInDate: String,
    checkOutDate: String,
    guests: String,
    onBackToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("✅ Booking Confirmed!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Text("Check-in: $checkInDate")
        Text("Check-out: $checkOutDate")
        Text("Guests: $guests")

        Spacer(modifier = Modifier.height(36.dp))

        Button(onClick = onBackToHome) {
            Text("Back to Home")
        }
    }
}