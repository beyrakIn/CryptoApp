package com.beyrak.crypto.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Config {

    companion object {
        var apiKey: String = "dfce78f5-66ec-42a4-8ed9-93db20aa7c93"
        var nKey: String = "67820bddf7ba08670db7921d13127386e992d009"

/*        var retrofit = Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var retrofit2 = Retrofit.Builder()
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