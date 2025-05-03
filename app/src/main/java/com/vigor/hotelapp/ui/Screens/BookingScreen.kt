package com.vigor.hotelapp.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vigor.hotelapp.navigation.Routes
import java.util.*

@Composable
fun BookingScreen(navController: NavController) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var checkInDate by remember { mutableStateOf("") }
    var checkOutDate by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf("1") }

    val openCheckInPicker = remember { mutableStateOf(false) }
    val openCheckOutPicker = remember { mutableStateOf(false) }

    if (openCheckInPicker.value) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                checkInDate = "$year-${month + 1}-$dayOfMonth"
                openCheckInPicker.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (openCheckOutPicker.value) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                checkOutDate = "$year-${month + 1}-$dayOfMonth"
                openCheckOutPicker.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Booking Details", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = checkInDate,
            onValueChange = {},
            label = { Text("Check-in Date") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { openCheckInPicker.value = true },
            readOnly = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = checkOutDate,
            onValueChange = {},
            label = { Text("Check-out Date") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { openCheckOutPicker.value = true },
            readOnly = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = guests,
            onValueChange = { guests = it },
            label = { Text("Guests") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (checkInDate.isNotBlank() && checkOutDate.isNotBlank() && guests.isNotBlank()) {
                    navController.navigate(
                        Routes.Confirmation.createRoute(
                            checkInDate,
                            checkOutDate,
                            guests
                        )
                    )
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Confirm Booking")
        }
    }
}