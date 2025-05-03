package com.vigor.hotelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vigor.hotelapp.viewmodel.HotelViewModel

@Composable
fun HotelDetailScreen(hotelId: Int?, navToBooking: () -> Unit) {
    val viewModel: HotelViewModel = viewModel()

    LaunchedEffect(hotelId) {
        hotelId?.let { viewModel.loadHotelById(it) }
    }

    val hotel by viewModel.selectedHotel.collectAsState()

    hotel?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = it.imageUrl,
                contentDescription = it.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(it.name, style = MaterialTheme.typography.headlineMedium)
            Text(it.location, style = MaterialTheme.typography.bodyMedium)
            Text("Rating: ${it.rating}", style = MaterialTheme.typography.bodyMedium)
            Text("Price: \$${it.pricePerNight} / night", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = navToBooking,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Book Now")
            }
        }
    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}