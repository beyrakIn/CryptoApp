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
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.messari.Data
import com.beyrak.crypto.enities.concretes.messari.Profile
import com.beyrak.crypto.views.CoinViewHolder
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random.Default.nextFloat


class HomeAdapter(private val coinList: List<Data>) : RecyclerView.Adapter<CoinViewHolder>() {
//    private val dataService2 = Config.retrofit2.create(ApiService::class.java)
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
        holder.coinPrice.text = ((Math.round(coin.metrics.market_data.price_usd * 1000.0) / 1000.0).toString()) + '$'
        holder.percent.text =
            (((coin.metrics.market_data.percent_change_usd_last_24_hours * 100.0).roundToInt() / 100.0).toString()) + "%"
        val rank= position + 1
        holder.coinRank.text = "#$rank"

        if (coin.metrics.market_data.percent_change_usd_last_24_hours > 0) {
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


            dataServiceMessari.getCoinProfile(coin.symbol).enqueue(object : Callback<Result<Profile>>{
                override fun onResponse(call: Call<Result<Profile>>, response: Response<Result<Profile>>) {
                    val profile: Result<Profile>? = response.body()
                    coinName.text = profile?.data?.name
                    coinSymbol.text = profile?.data?.symbol
                    coinPrice.text = ((Math.round(coin.metrics.market_data.price_usd * 1000.0) / 1000.0).toString()) + '$'
                    coinRank.text = "#$rank"
                    coinCredentials.text = "CATEGORY: " + profile?.data?.category +
                            "\n\n" + "SECTOR: " + profile?.data?.sector +
                            "\n\n" + "OVERVIEW: " + profile?.data?.overview +
                            "\n\n" + "TECHNOLOGY: " + profile?.data?.technology + "\n\n"

                    coinDesc.text = "DESCRIPTION: " + profile?.data?.token_distribution?.description

                    Picasso.get().load(logoUrl).into(coinLogo)
                }

                override fun onFailure(call: Call<Result<Profile>>, t: Throwable) {
                    println(t.localizedMessage)
                }
            })

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