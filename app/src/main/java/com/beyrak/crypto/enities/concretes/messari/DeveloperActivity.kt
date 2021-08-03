package com.beyrak.crypto.enities.concretes.messari

data class DeveloperActivity(
    val commits_last_1_year: Int,
    val commits_last_3_months: Int,
    val lines_added_last_1_year: Int,
    val lines_added_last_3_months: Int,
    val lines_deleted_last_1_year: Int,
    val lines_deleted_last_3_months: Int,
    val stars: Int,
    val watchers: Int
)