package com.beyrak.crypto.enities.concretes.messari

data class CycleLow(
    val at: String,
    val days_since: Int,
    val percent_up: Double,
    val price: Double
)