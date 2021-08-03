package com.beyrak.crypto.enities.concretes.messari

data class Data(
    val _internal_temp_agora_id: String,
    val contract_addresses: List<ContractAddresse>,
    val id: String,
    val metrics: Metrics,
    val name: String,
    val profile: Profile,
    val slug: String,
    val symbol: String
)