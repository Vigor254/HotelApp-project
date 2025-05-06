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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vigor.hotelapp.data.local.BookingEntity
import com.vigor.hotelapp.navigation.Routes
import com.vigor.hotelapp.viewmodel.BookingViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookingScreen(navController: NavController, viewModel: BookingViewModel = viewModel()) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var checkInDate by remember { mutableStateOf("") }
    var checkOutDate by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf("1") }
    var errorMessage by remember { mutableStateOf("") }

    val openCheckInPicker = remember { mutableStateOf(false) }
    val openCheckOutPicker = remember { mutableStateOf(false) }

    if (openCheckInPicker.value) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                checkInDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
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
                checkOutDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
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

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                errorMessage = ""
                if (checkInDate.isBlank() || checkOutDate.isBlank() || guests.isBlank()) {
                    errorMessage = "All fields are required."
                    return@Button
                }

                val guestsInt = guests.toIntOrNull()
                if (guestsInt == null || guestsInt <= 0) {
                    errorMessage = "Please enter a valid number of guests."
                    return@Button
                }

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                try {
                    val checkIn = dateFormat.parse(checkInDate)
                    val checkOut = dateFormat.parse(checkOutDate)
                    if (checkIn == null || checkOut == null || checkOut <= checkIn) {
                        errorMessage = "Check-out date must be after check-in date."
                        return@Button
                    }

                    val booking = BookingEntity(
                        checkIn = checkInDate,
                        checkOut = checkOutDate,
                        guests = guests
                    )
                    viewModel.addBooking(booking)
                    navController.navigate(
                        Routes.Confirmation.createRoute(
                            checkInDate,
                            checkOutDate,
                            guests
                        )
                    )
                } catch (e: Exception) {
                    errorMessage = "Invalid date format."
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Confirm Booking")
        }
    }
}