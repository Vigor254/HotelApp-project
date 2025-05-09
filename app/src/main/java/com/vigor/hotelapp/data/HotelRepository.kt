package com.vigor.hotelapp.data

import com.vigor.hotelapp.data.local.HotelDao
import com.vigor.hotelapp.data.local.HotelEntity
import com.vigor.hotelapp.data.local.toEntity
import com.vigor.hotelapp.data.local.toHotel
import com.vigor.hotelapp.model.Booking
import com.vigor.hotelapp.model.Hotel

class HotelRepository(private val hotelDao: HotelDao) {
    suspend fun getAllHotels(): List<Hotel> {
        return hotelDao.getAllHotels().map { it.toHotel() }
    }

    suspend fun getHotelById(hotelId: Int): Hotel? {
        return hotelDao.getHotelById(hotelId)?.toHotel()
    }

    suspend fun insertHotel(hotel: Hotel) {
        hotelDao.insertHotel(hotel.toEntity())
    }

    suspend fun insertBooking(booking: Booking) {
        hotelDao.insertBooking(booking)
    }

    suspend fun getBookingsByUser(userId: String): List<Booking> {
        return hotelDao.getBookingsByUser(userId)
    }
}