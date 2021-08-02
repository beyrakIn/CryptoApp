package com.beyrak.crypto.enities.concretes.blockchain

data class Wallet(
    val address: String,
    val final_balance: Double,
    val hash160: String,
    val n_tx: Int,
    val n_unredeemed: Int,
    val total_received: Double,
    val total_sent: Double,
    val txs: List<Tx>
)