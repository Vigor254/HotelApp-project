package com.vigor.hotelapp.data.repository

import com.vigor.hotelapp.data.model.Hotel

class HotelRepository {
    fun getHotels(): List<Hotel> {
        return listOf(
            Hotel(1, "Ocean View Resort", "Malibu", "https://via.placeholder.com/150", 120.0, 4.5f),
            Hotel(2, "City Lights Inn", "New York", "https://via.placeholder.com/150", 150.0, 4.2f),
            Hotel(3, "Mountain Escape", "Denver", "https://via.placeholder.com/150", 99.0, 4.7f)
        )
    }

    fun getHotelById(id: Int): Hotel? {
        return getHotels().find { it.id == id }
    }
}