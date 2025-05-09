package com.vigor.hotelapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vigor.hotelapp.data.HotelRepository
import com.vigor.hotelapp.model.Booking
import com.vigor.hotelapp.model.Hotel
import com.vigor.hotelapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    val repository: HotelRepository
) : ViewModel() {

    // State for hotels
    val hotels = mutableStateOf<List<Hotel>>(emptyList())

    // State for current user
    val currentUser = mutableStateOf<User?>(null)

    // State for bookings
    val bookings = mutableStateOf<List<Booking>>(emptyList())

    init {
        // Load hotels when ViewModel is created
        viewModelScope.launch {
            val currentHotels = repository.getAllHotels()
            if (currentHotels.isEmpty()) {
                // Insert initial hotels
                repository.insertHotel(
                    Hotel(
                        name = "Grand Hotel",
                        description = "A luxurious hotel with stunning views.",
                        imageUrl = "R.drawable.hotel1",
                        pricePerHour = 50.0,
                        location = "Downtown"
                    )
                )
                repository.insertHotel(
                    Hotel(
                        name = "Ocean Breeze",
                        description = "A beachfront hotel with relaxing vibes.",
                        imageUrl = "R.drawable.hotel2",
                        pricePerHour = 40.0,
                        location = "Beachside"
                    )
                )
                repository.insertHotel(
                    Hotel(
                        name = "Mountain Retreat",
                        description = "A cozy retreat in the mountains.",
                        imageUrl = "R.drawable.hotel3",
                        pricePerHour = 60.0,
                        location = "Mountains"
                    )
                )
            }
            loadHotels()
        }
    }

    fun loadHotels() {
        viewModelScope.launch {
            hotels.value = repository.getAllHotels()
        }
    }

    fun addHotel(hotel: Hotel) {
        viewModelScope.launch {
            repository.insertHotel(hotel)
            loadHotels() // Refresh the list
        }
    }


    fun bookHotel(hotelId: Int, hours: Int) {
        viewModelScope.launch {
            val hotel = repository.getHotelById(hotelId) ?: return@launch
            val totalCost = hotel.pricePerHour * hours
            val booking = Booking(
                userId = currentUser.value?.id ?: return@launch,
                hotelId = hotelId,
                hours = hours,
                totalCost = totalCost,
                bookingDate = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
            )
            repository.insertBooking(booking)
            loadBookings()
        }
    }

    fun loadBookings() {
        viewModelScope.launch {
            val userId = currentUser.value?.id ?: return@launch
            bookings.value = repository.getBookingsByUser(userId)
        }
    }

    // Mock user authentication
    fun login(email: String, password: String): Boolean {
        // Mock logic: Assume user exists if email and password match
        val user = User(id = email, password = password, isAdmin = email == "admin@hotelapp.com")
        currentUser.value = user
        loadBookings()
        return true
    }


    fun signup(email: String, password: String): Boolean {
        // Mock logic: Assume signup always succeeds
        val user = User(id = email, password = password)
        currentUser.value = user
        return true
    }

    fun logout() {
        currentUser.value = null
        bookings.value = emptyList()
    }
}
