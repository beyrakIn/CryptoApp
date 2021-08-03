package com.beyrak.crypto.enities.concretes.messari

data class OnChainData(
    val active_addresses: Int,
    val active_addresses_received_count: Int,
    val active_addresses_sent_count: Int,
    val addresses_balance_greater_0_001_native_units_count: Int,
    val addresses_balance_greater_0_01_native_units_count: Int,
    val addresses_balance_greater_0_1_native_units_count: Int,
    val addresses_balance_greater_100_native_units_count: Int,
    val addresses_balance_greater_100_usd_count: Int,
    val addresses_balance_greater_100k_native_units_count: Int,
    val addresses_balance_greater_100k_usd_count: Int,
    val addresses_balance_greater_10_native_units_count: Int,
    val addresses_balance_greater_10_usd_count: Int,
    val addresses_balance_greater_10k_native_units_count: Int,
    val addresses_balance_greater_10k_usd_count: Int,
    val addresses_balance_greater_10m_usd_count: Int,
    val addresses_balance_greater_1_native_units_count: Int,
    val addresses_balance_greater_1_usd_count: Int,
    val addresses_balance_greater_1k_native_units_count: Int,
    val addresses_balance_greater_1k_usd_count: Int,
    val addresses_balance_greater_1m_native_units_count: Int,
    val addresses_balance_greater_1m_usd_count: Int,
    val addresses_count: Int,
    val adjusted_nvt: Double,
    val adjusted_nvt_90d_moving_average: Double,
    val adjusted_rvt: Double,
    val adjusted_rvt_90d_moving_average: Double,
    val adjusted_txn_volume_last_24_hours_native_units: Double,
    val adjusted_txn_volume_last_24_hours_usd: Double,
    val average_block_gas_limit: Any,
    val average_block_interval: Double,
    val average_block_weight: Double,
    val average_fee_native_units: Double,
    val average_fee_usd: Double,
    val average_transfer_value_native_units: Double,
    val average_transfer_value_usd: Double,
    val average_txn_gas_limit: Any,
    val average_txn_gas_used: Any,
    val average_utxo_age: Double,
    val block_height: Int,
    val block_size_bytes_average: Double,
    val block_size_bytes_total: Int,
    val block_weight: Int,
    val blocks_added_last_24_hours: Int,
    val hash_rate: Double,
    val issuance_last_24_hours_native_units: Double,
    val issuance_rate: Double,
    val issuance_rate_daily: Double,
    val issuance_total_native_units: Double,
    val issuance_total_usd: Double,
    val median_fee_native_units: Double,
    val median_fee_usd: Double,
    val median_transfer_value_native_units: Double,
    val median_transfer_value_usd: Double,
    val median_utxo_age: Int,
    val mining_revenue_from_uncle_blocks_percent_last_24_hours: Any,
    val new_issuance: Double,
    val realized_marketcap_usd: Double,
    val total_fees_last_24_hours: Double,
    val total_fees_last_24_hours_usd: Double,
    val transfer_count_last_24_hours: Int,
    val transfer_erc721_count_last_24_hours: Any,
    val transfer_erc_20_count_last_24_hours: Any,
    val txn_contracts_calls_count_last_24_hours: Any,
    val txn_contracts_calls_success_count_last_24_hours: Any,
    val txn_contracts_count_last_24_hours: Any,
    val txn_contracts_creation_count_last_24_hours: Any,
    val txn_contracts_destruction_count_last_24_hours: Any,
    val txn_count_last_24_hours: Int,
    val txn_erc20_count_last_24_hours: Any,
    val txn_erc721_count_last_24_hours: Any,
    val txn_gas_limit_last_24_hours: Any,
    val txn_gas_used_last_24_hours: Any,
    val txn_per_second_count: Double,
    val txn_token_count_last_24_hours: Any,
    val txn_volume_last_24_hours_native_units: Double,
    val txn_volume_last_24_hours_usd: Double,
    val uncle_blocks_count_last_24_hours: Any,
    val uncle_rewards_last_24_hours_native_units: Any,
    val uncle_rewards_last_24_hours_usd: Any,
    val utxo_count_last_24_hours: Int,
    val utxo_in_loss_count: Int,
    val utxo_in_profit_count: Int,
    val value_weighted_average_utxo_age: Double
)