package com.beyrak.crypto.enities.concretes.blockchain

data class Tx(
    val balance: Any,
    val block_height: Int,
    val block_index: Int,
    val double_spend: Boolean,
    val fee: Int,
    val hash: String,
    val inputs: List<Input>,
    val lock_time: Int,
    val `out`: List<Out>,
    val relayed_by: String,
    val result: Int,
    val size: Int,
    val time: Int,
    val tx_index: Long,
    val ver: Int,
    val vin_sz: Int,
    val vout_sz: Int,
    val weight: Int
)