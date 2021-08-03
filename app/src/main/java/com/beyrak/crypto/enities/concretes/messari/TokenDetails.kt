package com.beyrak.crypto.enities.concretes.messari

data class TokenDetails(
    val block_reward: Double,
    val emission_type_general: String,
    val emission_type_precise: String,
    val genesis_block_date: String,
    val initial_supply: Double,
    val is_capped_supply: Boolean,
    val is_treasury_decentralized: Boolean,
    val is_victim_of_51_percent_attack: Boolean,
    val launch_style: String,
    val max_supply: Double,
    val mining_algorithm: String,
    val next_halving_date: String,
    val on_chain_governance_structure: Any,
    val percentage_allocated_to_investors_from_initial_supply: Double,
    val percentage_allocated_to_organizations_or_founders_supply: Double,
    val percentage_allocated_to_premined_or_airdrops_from_initial_supply: Double,
    val sales_rounds: Any,
    val targeted_block_time_in_sec: Int,
    val type: String,
    val usage: String
)