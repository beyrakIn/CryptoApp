package com.beyrak.crypto.enities.concretes.blockchain.general

data class GlobalMetrics(
    val activeCryptoCurrencies: Int,
    val activeExchanges: Int,
    val activeMarketPairs: Int,
    val btcDominance: Double,
    val btcDominance24hPercentageChange: Double,
    val btcDominanceYesterday: Double,
    val defi24hPercentageChange: Double,
    val defiMarketCap: Double,
    val defiVolume24h: Double,
    val defiVolume24hReported: Double,
    val derivatives24hPercentageChange: Double,
    val derivativesVolume24h: Double,
    val derivativesVolume24hReported: Double,
    val ethDominance: Double,
    val ethDominance24hPercentageChange: Double,
    val ethDominanceYesterday: Double,
    val etherscanGas: EtherscanGas,
    val quotes: List<Quote>,
    val stablecoin24hPercentageChange: Double,
    val stablecoinMarketCap: Double,
    val stablecoinVolume24h: Double,
    val stablecoinVolume24hReported: Double,
    val totalCryptoCurrencies: Int,
    val totalExchanges: Int
)