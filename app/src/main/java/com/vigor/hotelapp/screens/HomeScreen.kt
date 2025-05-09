package com.vigor.hotelapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.vigor.hotelapp.model.Hotel
import com.vigor.hotelapp.viewmodel.HotelViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HotelViewModel = hiltViewModel()) {
    val hotels by viewModel.hotels
    var currentIndex by remember { mutableStateOf(0) }

    // Auto-scroll carousel every 3 seconds
    LaunchedEffect(Unit) {
        while (hotels.isNotEmpty()) {
            delay(3000) // 3 seconds
            currentIndex = (currentIndex + 1) % hotels.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with Profile Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hotel App",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        if (viewModel.currentUser.value == null) {
                            navController.navigate("login")
                        } else {
                            navController.navigate("profile")
                        }
                    },
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Carousel for Hotels
        if (hotels.isNotEmpty()) {
            val hotel = hotels[currentIndex]
            HotelCarouselItem(
                hotel = hotel,
                onBookClick = {
                    if (viewModel.currentUser.value == null) {
                        navController.navigate("login")
                    } else {
                        // Navigate to booking screen if needed, but for now, we'll handle booking directly
                        // You can add a booking screen later
                        viewModel.bookHotel(hotel.id, hours = 2) // Example: 2 hours
                        navController.navigate("profile")
                    }
                }
            )
        } else {
            Text(text = "No hotels available", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun HotelCarouselItem(hotel: Hotel, onBookClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Hotel Image
            AsyncImage(
                model = hotel.imageUrl,
                contentDescription = hotel.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            // TODO: Add hotel images to your project in res/drawable and update the imageUrl in Hotel data to reference them, e.g., "R.drawable.hotel1"

            Spacer(modifier = Modifier.height(8.dp))

            // Hotel Name
            Text(
                text = hotel.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Hotel Description
            Text(
                text = hotel.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Hotel Location
            Text(
                text = "Location: ${hotel.location}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Price per Hour
            Text(
                text = "Price: $${hotel.pricePerHour}/hour",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Book Button
            Button(
                onClick = onBookClick,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Book Now", color = Color.White)
            }
        }
    }
}
