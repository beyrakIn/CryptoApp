package com.beyrak.crypto.enities.concretes

data class CoinDetails(
    val category: String,
    val dateAdded: String,
    val description: String,
    val holders: Holders,
    val id: Int,
    val isAudited: Boolean,
    val latestUpdateTime: String,
    val launchPrice: Double,
    val name: String,
    val notice: String,
    val relatedCoins: List<RelatedCoin>,
    val relatedExchanges: List<RelatedExchange>,
    val slug: String,
    val statistics: Statistics,
    val status: String,
    val symbol: String,
    val tags: List<Tag>,
    val urls: Urls,
    val volume: Double,
    val volumeChangePercentage24h: Double,
    val wallets: List<Wallet>,
    val watchCount: String
)