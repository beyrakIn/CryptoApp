package com.beyrak.crypto.api

import com.beyrak.crypto.enities.concretes.Coin
import com.beyrak.crypto.enities.concretes.Map
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.blockchain.Wallet
import com.beyrak.crypto.enities.concretes.coinmarketcal.Response
import com.beyrak.crypto.enities.concretes.messari.Data
import com.beyrak.crypto.enities.concretes.messari.Market
import com.beyrak.crypto.enities.concretes.messari.News
import com.beyrak.crypto.enities.concretes.messari.Profile
import com.beyrak.crypto.enities.concretes.spark.Spark
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

/*
    @GET("cryptocurrency/listings/latest?limit=1000")
    fun getData(@Header("X-CMC_PRO_API_KEY") API_KEY: String): Call<Result<List<Coin>>>

    @GET("cryptocurrency/map?sort=cmc_rank&limit=200")
    fun getMap(@Header("X-CMC_PRO_API_KEY") API_KEY: String): Call<Result<Map>>
*/

    //Messari
    @GET("assets?fields=id,slug,symbol,metrics/market_data/percent_change_usd_last_24_hours,metrics/market_data/price_usd&limit=1000")
    fun getCoins(): Call<Result<List<Data>>>

    @GET("markets")
    fun getMarkets(): Call<Result<List<Market>>>

    @GET("news")
    fun getNews(): Call<Result<List<News>>>

    @GET("assets/{symbol}/profile")
    fun getCoinProfile(
        @Path("symbol") symbol: String
    ): Call<Result<Profile>>

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

    //CoinMarketCal
    @GET("coins")
    fun getCalCoins(@Header("x-api-key") API_KEY: String): Call<Response>


}