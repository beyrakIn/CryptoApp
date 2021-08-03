package com.beyrak.crypto.enities.concretes.messari

data class MiningStats(
    val `1_hour_attack_cost`: Any,
    val `24_hours_attack_cost`: Any,
    val attack_appeal: Any,
    val available_on_nicehash_percent: Double,
    val average_difficulty: Double,
    val hash_rate: Double,
    val hash_rate_30d_average: Double,
    val mining_algo: String,
    val mining_revenue_from_fees_percent_last_24_hours: Double,
    val mining_revenue_native: Double,
    val mining_revenue_per_hash_native_units: Double,
    val mining_revenue_per_hash_per_second_native_units: Double,
    val mining_revenue_per_hash_per_second_usd: Double,
    val mining_revenue_per_hash_usd: Double,
    val mining_revenue_total: Double,
    val mining_revenue_usd: Int,
    val network_hash_rate: String
)