package com.beyrak.crypto.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.beyrak.crypto.MainActivity
import com.beyrak.crypto.R
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.enities.concretes.CoinDetails
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.coinmarketcap.CryptoCurrencyMap
import com.beyrak.crypto.views.CoinViewHolder
import com.squareup.picasso.Picasso
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class HomeAdapter(private val coinList: List<CryptoCurrencyMap>) :
    RecyclerView.Adapter<CoinViewHolder>() {
    private val dataServiceCap = Config.retrofitCap.create(ApiService::class.java)


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.vertical_crypto_card, parent, false
        )
        return CoinViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinList[position]
        var coinDetails: CoinDetails

        holder.coinSymbol.text = coin.symbol
        holder.coinRank.text = '#' + coin.rank.toString()


        val logoUrl: String =
            "https://s2.coinmarketcap.com/static/img/coins/128x128/" + coin.id + ".png"

        Picasso.get().load(logoUrl).into(holder.coinLogo)










        dataServiceCap.getCapCoinDetails(coin.id)
            .enqueue(object : Callback<Result<CoinDetails>> {
                override fun onResponse(
                    call: Call<Result<CoinDetails>>,
                    response: Response<Result<CoinDetails>>
                ) {
                    if (response.isSuccessful) {
                        coinDetails = response.body()!!.data

                        holder.coinPrice.text =
                            round(coinDetails.statistics.price, 100.0).toString() + '$'

                        if (coinDetails.statistics.priceChangePercentage24h > 0) {
                            holder.apply {
                                percent.setTextColor(Color.GREEN)
                                sparkView.lineColor = Color.GREEN
                                percent.text = '↑' + round(
                                    coinDetails.statistics.priceChangePercentage24h,
                                    100.0
                                ).toString() + "%"
                            }
                        } else {
                            holder.apply {
                                percent.setTextColor(Color.RED)
                                sparkView.lineColor = Color.RED
                                percent.text = '↓' + round(
                                    coinDetails.statistics.priceChangePercentage24h, 100.0
                                ).toString() + "%"
                            }
                        }
                    } else {
                        alert(
                            holder.itemView.context as Activity,
                            "Error",
                            response.errorBody()!!.string()
                        )
                    }
                }

                override fun onFailure(call: Call<Result<CoinDetails>>, t: Throwable) {
                    alert(
                        holder.itemView.context as Activity,
                        "Error",
                        t.localizedMessage!!
                    )
                }

            })

        holder.itemView.setOnClickListener {
            val dialog =
                BottomSheetDialog(holder.view.context)
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


            dataServiceCap.getCapCoinDetails(coin.id)
                .enqueue(object : Callback<Result<CoinDetails>> {
                    override fun onResponse(
                        call: Call<Result<CoinDetails>>,
                        response: Response<Result<CoinDetails>>
                    ) {
                        if (response.isSuccessful) {
                            val coinDetails: CoinDetails = response.body()!!.data
                            coinName.text = coinDetails.name
                            coinSymbol.text = coinDetails.symbol
                            coinPrice.text =
                                round(coinDetails.statistics.price, 1000.0).toString() + '$'
                            coinRank.text = '#' + coin.rank.toString()
//                            coinCredentials.text = "OVERVIEW: " + coinDetails.category + "\n\n"
                            coinDesc.text = coinDetails.description

                            Picasso.get().load(logoUrl).into(coinLogo)
                        } else {
                            alert(
                                holder.itemView.context as Activity,
                                "Error",
                                response.errorBody()!!.string()
                            )
                        }
                    }

                    override fun onFailure(call: Call<Result<CoinDetails>>, t: Throwable) {
                        alert(
                            holder.itemView.context as Activity, "Error", t.localizedMessage!!
                        )
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

    fun alert(parent: Activity, title: String, text: String) {
        Alerter.create(parent)
            .setTitle(title)
            .setText(text)
            .setBackgroundColorRes(R.color.purple_200)
            .show()
    }

    fun round(num: Double, percent: Double): Double {
        return ((num * percent).roundToInt() / percent)
    }
}