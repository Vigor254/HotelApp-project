package com.vigor.hotelapp.data

import android.content.Context
import com.vigor.hotelapp.data.local.AppDatabase
import com.vigor.hotelapp.data.local.BookingEntity

class BookingRepository(context: Context) {
    private val bookingDao = AppDatabase.Companion.getDatabase(context).bookingDao()

    suspend fun addBooking(booking: BookingEntity) {
        bookingDao.insertBooking(booking)
    }

    suspend fun getBookings(): List<BookingEntity> {
        return bookingDao.getAllBookings()
    }
}