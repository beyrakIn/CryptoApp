package com.beyrak.crypto.enities.concretes

data class Map(
    val first_historical_data: String,
    val id: Int,
    val is_active: Int,
    val last_historical_data: String,
    val name: String,
    val platform: Any,
    val rank: Int,
    val slug: String,
    val symbol: String
)