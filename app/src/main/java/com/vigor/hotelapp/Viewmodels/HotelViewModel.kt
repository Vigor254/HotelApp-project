package com.vigor.hotelapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vigor.hotelapp.data.model.Hotel
import com.vigor.hotelapp.data.repository.HotelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HotelViewModel : ViewModel() {

    private val repository = HotelRepository()

    private val _hotelList = MutableStateFlow<List<Hotel>>(emptyList())
    val hotelList: StateFlow<List<Hotel>> = _hotelList

    init {
        loadHotels()
    }

    private fun loadHotels() {
        viewModelScope.launch {
            _hotelList.value = repository.getHotels()
        }
    }
    private val _selectedHotel = MutableStateFlow<Hotel?>(null)
    val selectedHotel: StateFlow<Hotel?> = _selectedHotel

    fun loadHotelById(id: Int) {
        viewModelScope.launch {
            _selectedHotel.value = repository.getHotelById(id)
        }
    }

}
