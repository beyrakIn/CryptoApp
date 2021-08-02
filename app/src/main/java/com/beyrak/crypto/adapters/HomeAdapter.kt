package com.beyrak.crypto.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.beyrak.crypto.R
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.enities.concretes.Coin
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.messari.Data
import com.beyrak.crypto.views.CoinViewHolder
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextFloat


class HomeAdapter(private val coinList: List<Coin>) : RecyclerView.Adapter<CoinViewHolder>() {
    private val dataService2 = Config.retrofit2.create(ApiService::class.java)
    private val dataServiceMessari = Config.retrofitMessari.create(ApiService::class.java)


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.vertical_crypto_card,
            parent, false
        )
        return CoinViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinList[position]
        holder.coinSymbol.text = coin.symbol
        holder.coinPrice.text = ((Math.round(coin.quote.USD.price * 1000.0) / 1000.0).toString()) + '$'
        holder.percent.text =
            ((Math.round(coin.quote.USD.percent_change_24h * 100.0) / 100.0).toString()) + "%" // coin.quote.USD.percent_change_24h.toString()
        holder.coinRank.text = '#' + coin.cmc_rank.toString()

        if (coin.quote.USD.percent_change_24h > 0) {
            holder.percent.setTextColor(Color.GREEN)
            holder.sparkView.lineColor = Color.GREEN
        } else {
            holder.percent.setTextColor(Color.RED)
            holder.sparkView.lineColor = Color.RED
        }

        val logoUrl: String =
            "https://s2.coinmarketcap.com/static/img/coins/128x128/" + coin.id + ".png"

        Picasso.get().load(logoUrl).into(holder.coinLogo)

        val randomValues = List(30) { nextFloat() + 5 }


        holder.sparkView.adapter = SparkViewAdapter(randomValues)
        holder.sparkView.lineWidth = 4F

        val currentTime: String = SimpleDateFormat("yyyy-MM-dd'T'h:m:ss").format(Date())+'Z'
/*
        dataService2.getCoinSpark(Config.nKey, coin.symbol, "2021-07-29T11:40:28Z", "2021-07-30T11:40:28Z")
            .enqueue(object : Callback<List<Spark>>{
                override fun onResponse(call: Call<List<Spark>>, response: Response<List<Spark>>) {
                    if (response.isSuccessful){
                        val sparkList: MutableList<Float> = ArrayList()
                        for (i in response.body()?.get(0)?.prices!!){
                            sparkList += i.toFloat()
                        }

                        holder.sparkView.adapter = SparkViewAdapter(sparkList)
                        holder.sparkView.lineWidth = 7F
                    }else{
                        println("=================================")
                        println(response.message())
                        println(response.errorBody()?.string())
                        println(currentTime)
                        println("=================================")
                        Toast.makeText(
                            holder.view.context, response.errorBody()?.string(), Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Spark>>, t: Throwable) {
                    Toast.makeText(
                        holder.view.context, t.localizedMessage, Toast.LENGTH_SHORT
                    ).show()
                }

            })

*/



        holder.itemView.setOnClickListener {
            val dialog = BottomSheetDialog(holder.view.context)
            val view =
                LayoutInflater.from(holder.view.context).inflate(R.layout.coin_bottom_sheet, null)

            val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
            val coinLogo = view.findViewById<ImageView>(R.id.coinLogo)
            val coinName = view.findViewById<TextView>(R.id.coinName)
            val coinPrice = view.findViewById<TextView>(R.id.coinPrice)
            val coinSymbol = view.findViewById<TextView>(R.id.coinSymbol)
            val coinCredentials = view.findViewById<TextView>(R.id.coinCredentials)
            val coinDesc = view.findViewById<TextView>(R.id.coinDescription)
            val coinRank = view.findViewById<TextView>(R.id.coinRank)


            dataServiceMessari.getCoinProfile(coin.symbol).enqueue(object : Callback<Result<Data>>{
                override fun onResponse(call: Call<Result<Data>>, response: Response<Result<Data>>) {
                    val token: Result<Data>? = response.body()
                    coinName.text = token?.data?.name
                    coinSymbol.text = token?.data?.symbol
                    coinPrice.text = ((Math.round(coin.quote.USD.price * 1000.0) / 1000.0).toString()) + '$'
                    coinRank.text = '#' + coin.cmc_rank.toString()
                    coinCredentials.text = "CATEGORY: " + token?.data?.category +
                            "\n\n" + "SECTOR: " + token?.data?.sector +
                            "\n\n" + "OVERVIEW: " + token?.data?.overview +
                            "\n\n" + "TECHNOLOGY: " + token?.data?.technology + "\n\n"

                    coinDesc.text = "DESCRIPTION: " + token?.data?.token_distribution?.description

                    Picasso.get().load(logoUrl).into(coinLogo)
                }

                override fun onFailure(call: Call<Result<Data>>, t: Throwable) {
                    println(t.localizedMessage)
                    Toast.makeText(view.context, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

/*
            dataService2.getCoinData(Config.nKey, coin.symbol, 1, "USD")
                .enqueue(object : Callback<List<CoinItem>> {
                    override fun onResponse(
                        call: Call<List<CoinItem>>,
                        response: Response<List<CoinItem>>
                    ) {
                        if (response.isSuccessful) {
                            val token: CoinItem? = response.body()?.get(0)
                            coinName.text = token?.name
                            coinSymbol.text = token?.symbol
                            coinRank.text = token?.rank

                            Picasso.get().load(logoUrl).into(coinLogo)

                        } else {
                            Toast.makeText(
                                holder.view.context, response.errorBody()?.string(), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<List<CoinItem>>, t: Throwable) {
                        Toast.makeText(
                            holder.view.context, t.localizedMessage, Toast.LENGTH_SHORT
                        ).show()
                    }

                })

*/


            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()



            btnClose.setOnClickListener {
                dialog.dismiss()
            }
        }

    }

    override fun getItemCount(): Int {
        return coinList.size
    }
}