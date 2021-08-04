package com.beyrak.crypto.enities.concretes.spark

data class Spark(
    val _internal_temp_agora_id: String,
    val contract_addresses: List<Any>,
    val id: String,
    val name: String,
    val parameters: Parameters,
    val schema: Schema,
    val slug: String,
    val symbol: String,
    val values: List<List<Double>>
)