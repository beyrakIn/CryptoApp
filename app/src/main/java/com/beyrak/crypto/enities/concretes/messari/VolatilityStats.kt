package com.beyrak.crypto.enities.concretes.messari

data class VolatilityStats(
    val volatility_last_1_year: Double,
    val volatility_last_30_days: Double,
    val volatility_last_3_years: Double,
    val volatility_last_90_days: Double
)