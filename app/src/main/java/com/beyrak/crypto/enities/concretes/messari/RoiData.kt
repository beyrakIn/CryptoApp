package com.beyrak.crypto.enities.concretes.messari

data class RoiData(
    val percent_change_btc_last_1_month: Int,
    val percent_change_btc_last_1_week: Int,
    val percent_change_btc_last_1_year: Int,
    val percent_change_btc_last_3_months: Int,
    val percent_change_eth_last_1_month: Double,
    val percent_change_eth_last_1_week: Double,
    val percent_change_eth_last_1_year: Double,
    val percent_change_eth_last_3_months: Double,
    val percent_change_last_1_month: Double,
    val percent_change_last_1_week: Double,
    val percent_change_last_1_year: Double,
    val percent_change_last_3_months: Double,
    val percent_change_month_to_date: Double,
    val percent_change_quarter_to_date: Double,
    val percent_change_year_to_date: Double
)