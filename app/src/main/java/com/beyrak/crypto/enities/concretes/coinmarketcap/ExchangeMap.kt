package com.beyrak.crypto.enities.concretes.coinmarketcap

data class ExchangeMap(
    val first_historical_data: String,
    val id: Int,
    val is_active: Int,
    val last_historical_data: String,
    val name: String,
    val slug: String,
    val status: String
)