package com.vigor.hotelapp.model

data class Hotel(
    val id: Int = 0,
    val name: String,
    val description: String,
    val imageUrl: String,
    val pricePerHour: Double,
    val location: String
)