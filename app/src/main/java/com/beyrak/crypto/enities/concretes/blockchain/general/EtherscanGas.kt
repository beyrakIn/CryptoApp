package com.beyrak.crypto.enities.concretes.blockchain.general

data class EtherscanGas(
    val fastConfirmationTime: String,
    val fastPrice: String,
    val lastBlock: String,
    val slowConfirmationTime: String,
    val slowPrice: String,
    val standardConfirmationTime: String,
    val standardPrice: String
)