package com.vigor.hotelapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val hotelId: Int,
    val hours: Int,
    val totalCost: Double,
    val bookingDate: String
)