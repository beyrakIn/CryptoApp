package com.beyrak.crypto.enities.concretes

data class Result<T>(
    val data: T,
    val status: Status
)