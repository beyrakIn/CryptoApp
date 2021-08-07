package com.beyrak.crypto.enities.concretes

data class Holders(
    val dailyActive: Int,
    val holderCount: Int,
    val holderList: List<Holder>,
    val topFiftyHolderRatio: Double,
    val topHundredHolderRatio: Double,
    val topTenHolderRatio: Double,
    val topTwentyHolderRatio: Double
)