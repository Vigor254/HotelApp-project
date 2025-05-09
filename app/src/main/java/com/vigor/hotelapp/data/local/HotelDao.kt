package com.vigor.hotelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vigor.hotelapp.model.Booking

@Dao
interface HotelDao {
    @Insert
    suspend fun insertHotel(hotel: HotelEntity)

    @Query("SELECT * FROM hotels")
    suspend fun getAllHotels(): List<HotelEntity>

    @Query("SELECT * FROM hotels WHERE id = :hotelId")
    suspend fun getHotelById(hotelId: Int): HotelEntity?

    @Insert
    suspend fun insertBooking(booking: Booking)

    @Query("SELECT * FROM bookings WHERE userId = :userId")
    suspend fun getBookingsByUser(userId: String): List<Booking>
}