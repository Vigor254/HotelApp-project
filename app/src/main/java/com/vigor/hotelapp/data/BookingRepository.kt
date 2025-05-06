package com.vigor.hotelapp.data

import com.vigor.hotelapp.data.local.BookingDao
import com.vigor.hotelapp.data.local.BookingEntity

class BookingRepository(private val bookingDao: BookingDao) {

    suspend fun addBooking(booking: BookingEntity) {
        bookingDao.insertBooking(booking)
    }

    suspend fun getBookings(): List<BookingEntity> {
        return bookingDao.getAllBookings()
    }
}