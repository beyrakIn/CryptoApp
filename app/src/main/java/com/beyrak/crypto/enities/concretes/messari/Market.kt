package com.beyrak.crypto.enities.concretes.messari

data class Market(
    val base_asset_id: String,
    val base_asset_symbol: String,
    val `class`: String,
    val deviation_from_vwap_percent: Any,
    val exchange_id: String,
    val exchange_name: String,
    val exchange_slug: String,
    val excluded_from_price: Boolean,
    val has_real_volume: Boolean,
    val id: String,
    val last_trade_at: Any,
    val pair: String,
    val price_usd: Any,
    val quote_asset_id: String,
    val quote_asset_symbol: String,
    val trade_end: Any,
    val trade_start: String,
    val version: Int,
    val volume_last_24_hours: Any,
    val vwap_weight: Int
)