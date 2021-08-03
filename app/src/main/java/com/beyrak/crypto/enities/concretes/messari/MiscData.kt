package com.beyrak.crypto.enities.concretes.messari

data class MiscData(
    val asset_age_days: Int,
    val asset_created_at: String,
    val btc_current_normalized_supply_price_usd: Double,
    val btc_y2050_normalized_supply_price_usd: Double,
    val categories: List<String>,
    val private_market_price_usd: Any,
    val sectors: List<String>,
    val tags: Any,
    val vladimir_club_cost: Double
)