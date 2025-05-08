package com.vigor.hotelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vigor.hotelapp.navigation.Routes
import com.vigor.hotelapp.ui.components.HotelItem
import com.vigor.hotelapp.viewmodel.HotelViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HotelViewModel = viewModel()
    val hotels by viewModel.hotelList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search hotels...") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(hotels) { hotel ->
                HotelItem(hotel = hotel) {
                    navController.navigate(Routes.Details.createRoute(hotel.id))
                }
            }
        }
    }
}
