package com.beyrak.crypto.enities.concretes.messari

data class Metrics(
    val alert_messages: Any,
    val all_time_high: AllTimeHigh,
    val blockchain_stats_24_hours: BlockchainStats24Hours,
    val cycle_low: CycleLow,
    val developer_activity: DeveloperActivity,
    val exchange_flows: ExchangeFlows,
    val market_data: MarketData,
    val market_data_liquidity: MarketDataLiquidity,
    val marketcap: Marketcap,
    val miner_flows: MinerFlows,
    val mining_stats: MiningStats,
    val misc_data: MiscData,
    val on_chain_data: OnChainData,
    val reddit: Reddit,
    val risk_metrics: RiskMetrics,
    val roi_by_year: RoiByYear,
    val roi_data: RoiData,
    val staking_stats: StakingStats,
    val supply: Supply,
    val supply_activity: SupplyActivity,
    val supply_distribution: SupplyDistribution,
    val token_sale_stats: TokenSaleStats
)