
package com.vigor.hotelapp.data.model
data class Hotel(
    val id: Int,
    val name: String,
    val location: String,
    val imageUrl: String,
    val pricePerNight: Double,
    val rating: Float
)
