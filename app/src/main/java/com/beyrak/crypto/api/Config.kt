package com.beyrak.crypto.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Config {

    companion object {
        var retrofitCap = Retrofit.Builder()
            .baseUrl("https://api.coinmarketcap.com/data-api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

/*    var retrofit2 = Retrofit.Builder()
        .baseUrl("https://api.nomics.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()*/

        var retrofitMessari = Retrofit.Builder()
            .baseUrl("https://data.messari.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitBlockchain = Retrofit.Builder()
            .baseUrl("https://blockchain.info/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitCal = Retrofit.Builder()
            .baseUrl("https://developers.coinmarketcal.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}