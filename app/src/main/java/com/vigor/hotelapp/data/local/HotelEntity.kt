package com.vigor.hotelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vigor.hotelapp.model.Hotel

@Entity(tableName = "hotels")
data class HotelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val imageUrl: String,
    val pricePerHour: Double,
    val location: String
)

// Convert between Entity and Model
fun HotelEntity.toHotel() = Hotel(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    pricePerHour = pricePerHour,
    location = location
)

fun Hotel.toEntity() = HotelEntity(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    pricePerHour = pricePerHour,
    location = location
)