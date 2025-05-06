package com.vigor.hotelapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vigor.hotelapp.data.BookingRepository
import com.vigor.hotelapp.data.local.BookingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val repository: BookingRepository
) : ViewModel() {

    private val _bookings = MutableStateFlow<List<BookingEntity>>(emptyList())
    val bookings: StateFlow<List<BookingEntity>> = _bookings

    fun fetchBookings() {
        viewModelScope.launch {
            _bookings.value = repository.getBookings()
        }
    }

    fun addBooking(booking: BookingEntity) {
        viewModelScope.launch {
            repository.addBooking(booking)
            fetchBookings()
        }
    }
}