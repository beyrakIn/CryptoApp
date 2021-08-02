package com.beyrak.crypto.enities.concretes.messari

data class TokenDistribution(
    val current_supply: Any,
    val description: String,
    val initial_distribution: Double,
    val max_supply: Double,
    val sale_end: Any,
    val sale_start: Any
)