package com.beyrak.crypto.api

import com.beyrak.crypto.enities.concretes.CoinDetails
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.blockchain.Wallet
import com.beyrak.crypto.enities.concretes.blockchain.general.GlobalMetrics
import com.beyrak.crypto.enities.concretes.coinmarketcap.Data
import com.beyrak.crypto.enities.concretes.messari.News
import com.beyrak.crypto.enities.concretes.spark.Spark
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("news")
    fun getNews(
        @Query("limit") limit: Int
    ): Call<Result<List<News>>>

    @GET("assets/{coin}/metrics/price/time-series")
    fun getSparkData(
        @Path("coin") coin: String,
//        @Query("startDate") startDate: String,
//        @Query("stopDate") stopDate: String,
//        @Query("interval") interval: String
    ): Call<Result<Spark>>


    //Blockchain
    @GET("rawaddr/{wallet}")
    fun getWallet(@Path("wallet") wallet: String): Call<Wallet>

    //CoinMarketCap
    @GET("global-metrics/quotes/latest")
    fun getGlobalMetrics(): Call<Result<GlobalMetrics>>

    @GET("map/all")
    fun getCapCoins(): Call<Result<Data>>

    @GET("cryptocurrency/detail")
    fun getCapCoinDetails(
        @Query("id") id: Int
    ): Call<Result<CoinDetails>>


}