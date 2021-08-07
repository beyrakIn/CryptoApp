package com.beyrak.crypto.enities.concretes

data class RelatedCoin(
    val id: Int,
    val name: String,
    val price: Double,
    val priceChangePercentage24h: Double,
    val priceChangePercentage7d: Double,
    val slug: String
)