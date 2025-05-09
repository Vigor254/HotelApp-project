package com.vigor.hotelapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vigor.hotelapp.model.Hotel
import com.vigor.hotelapp.viewmodel.HotelViewModel

@Composable
fun AdminPanelScreen(navController: NavHostController, viewModel: HotelViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var pricePerHour by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with Back Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "Admin Panel",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(32.dp)) // Placeholder for symmetry
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Form to Add Hotel
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Hotel Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Image URL (e.g., R.drawable.hotel1)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = pricePerHour,
            onValueChange = { pricePerHour = it },
            label = { Text("Price per Hour") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Error Message
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Add Hotel Button
        Button(
            onClick = {
                if (name.isBlank() || description.isBlank() || imageUrl.isBlank() || pricePerHour.isBlank() || location.isBlank()) {
                    errorMessage = "Please fill in all fields"
                } else {
                    val price = pricePerHour.toDoubleOrNull()
                    if (price == null) {
                        errorMessage = "Invalid price format"
                    } else {
                        val hotel = Hotel(
                            name = name,
                            description = description,
                            imageUrl = imageUrl,
                            pricePerHour = price,
                            location = location
                        )
                        viewModel.addHotel(hotel)
                        // Reset fields
                        name = ""
                        description = ""
                        imageUrl = ""
                        pricePerHour = ""
                        location = ""
                        errorMessage = "Hotel added successfully"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Hotel")
        }
    }
}