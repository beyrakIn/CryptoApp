package com.beyrak.crypto.enities.concretes.blockchain.general

data class Quote(
    val altcoinMarketCap: Double,
    val altcoinVolume24H: Double,
    val altcoinVolume24HReported: Double,
    val cryptoId: Int,
    val defi24HPercentageChange: Double,
    val defiMarketCap: Double,
    val defiVolume24H: Double,
    val defiVolume24HReported: Double,
    val derivatives24HPercentageChange: Double,
    val derivativesVolume24H: Double,
    val derivativesVolume24HReported: Double,
    val last_updated: String,
    val stablecoin24HPercentageChange: Double,
    val stablecoinMarketCap: Double,
    val stablecoinVolume24H: Double,
    val stablecoinVolume24HReported: Double,
    val totalMarketCap: Double,
    val totalMarketCapYesterday: Double,
    val totalMarketCapYesterdayPercentageChange: Double,
    val totalVolume24H: Double,
    val totalVolume24HReported: Double,
    val totalVolume24HYesterday: Double,
    val totalVolume24HYesterdayPercentageChange: Double
)