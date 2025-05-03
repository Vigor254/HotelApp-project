package com.vigor.hotelapp.data.local

import androidx.room.*

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: BookingEntity)

    @Query("SELECT * FROM bookings ORDER BY id DESC")
    suspend fun getAllBookings(): List<BookingEntity>
}
