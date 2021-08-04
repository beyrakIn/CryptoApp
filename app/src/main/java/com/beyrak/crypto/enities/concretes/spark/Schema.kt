package com.beyrak.crypto.enities.concretes.spark

data class Schema(
    val description: String,
    val metric_id: String,
    val minimum_interval: String,
    val values_schema: ValuesSchema
)