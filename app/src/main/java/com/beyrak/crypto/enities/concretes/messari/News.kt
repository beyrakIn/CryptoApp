package com.beyrak.crypto.enities.concretes.messari

data class News(
    val author: Author,
    val content: String,
    val id: String,
    val published_at: String,
    val reference_title: Any,
    val references: List<Reference>,
    val tags: List<Any>,
    val title: String,
    val url: String
)