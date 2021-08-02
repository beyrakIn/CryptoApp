package com.beyrak.crypto.enities.concretes.blockchain

data class Out(
    val addr: String,
    val n: Int,
    val script: String,
    val spending_outpoints: List<SpendingOutpointX>,
    val spent: Boolean,
    val tx_index: Long,
    val type: Int,
    val value: Int
)