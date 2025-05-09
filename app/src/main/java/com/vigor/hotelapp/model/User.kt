package com.vigor.hotelapp.model

data class User(
    val id: String,
    val password: String,
    val isAdmin: Boolean = false
)