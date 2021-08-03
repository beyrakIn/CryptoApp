package com.beyrak.crypto.enities.concretes.messari

data class Profile(
    val name: String,
    val symbol: String,
    val background: String,
    val category: String,
    val consensus_algorithm: String,
    val is_verified: Boolean,
    val organizations: List<Organization>,
    val overview: String,
    val people: People,
    val relevant_resources: List<RelevantResource>,
    val sector: String,
    val sfarScore: Double,
    val tag: String,
    val tagline: String,
    val technology: String,
    val token_details: TokenDetails,
    val token_distribution: TokenDistribution
)