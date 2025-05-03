package com.vigor.hotelapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.vigor.hotelapp.data.BookingRepository

import com.vigor.hotelapp.data.local.BookingEntity
import kotlinx.coroutines.launch

class BookingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BookingRepository(application)

    private val _bookings = MutableLiveData<List<BookingEntity>>()
    val bookings: LiveData<List<BookingEntity>> = _bookings

    fun fetchBookings() {
        viewModelScope.launch {
            _bookings.value = repository.getBookings()
        }
    }

    fun addBooking(booking: BookingEntity) {
        viewModelScope.launch {
            repository.addBooking(booking)
            fetchBookings() // Refresh after adding
        }
    }
}

