package com.beyrak.crypto.enities.concretes.coinmarketcap

data class CryptoCurrencyMap(
    val first_historical_data: String,
    val id: Int,
    val is_active: Int,
    val last_historical_data: String,
    val name: String,
    val platform: Platform,
    val rank: Int,
    val slug: String,
    val status: String,
    val symbol: String
)