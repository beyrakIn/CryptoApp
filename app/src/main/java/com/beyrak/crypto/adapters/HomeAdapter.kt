package com.beyrak.crypto.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.beyrak.crypto.Constants
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
//    lateinit val coinDetails: CoinDetails

    companion object {
        const val RETRY_TIME: Long = 5000
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.vertical_crypto_card, parent, false
        )
        return CoinViewHolder(view)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinList[position]

        holder.coinSymbol.text = coin.symbol
        holder.coinRank.text = '#' + coin.rank.toString()

        val logoUrl: String =
            "https://s2.coinmarketcap.com/static/img/coins/128x128/" + coin.id + ".png"
        Picasso.get().load(logoUrl).into(holder.coinLogo)


        if (holder.itemView.context?.let { Constants.isOnline(it) } == true) {
            getData(coin, holder)
        } else {
            alert(
                holder.itemView.context as Activity,
                "Network Problem", "Please check your internet connection."
            )
        }

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun getData(coin: CryptoCurrencyMap, holder: CoinViewHolder) {

        var coinDetail: CoinDetails? = null

        dataServiceCap.getCapCoinDetails(coin.id)
            .enqueue(object : Callback<Result<CoinDetails>> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<Result<CoinDetails>>,
                    response: Response<Result<CoinDetails>>
                ) {
                    if (response.isSuccessful) {
                        val coinDetails = response.body()!!.data
                        coinDetail = coinDetails

                        holder.coinPriceProgress.visibility = View.GONE
                        holder.coinPrice.visibility = View.VISIBLE
                        try {
                            coinDetails.statistics.price.let {
                                holder.coinPrice.text = round(it, 100.0).toString() + '$'
                            }

                            if (coinDetails.statistics.priceChangePercentage24h > 0) {
                                holder.apply {
                                    percent.setTextColor(Color.GREEN)
                                    sparkView.lineColor = Color.GREEN
                                    percent.text = '↑' + round(
                                        coinDetails.statistics.priceChangePercentage24h, 100.0
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
                        } catch (e: Exception) {
//                            println("errorr2" + e.message)
                        }
                    } else {
//                        alert(
//                            holder.itemView.context as Activity,
//                            "Error", response.errorBody()!!.string()
//                        )
                        Handler().postDelayed({ getData(coin, holder) }, 1000)
                    }
                }

                override fun onFailure(call: Call<Result<CoinDetails>>, t: Throwable) {
//                    alert(
//                        holder.itemView.context as Activity,
//                        "Error", t.localizedMessage!!
//                    )
                    Handler().postDelayed({ getData(coin, holder) }, 1000)

                }
            })


        holder.itemView.setOnClickListener {
            val dialog = BottomSheetDialog(holder.view.context)
            val view = LayoutInflater.from(
                holder.view.context
            ).inflate(R.layout.coin_bottom_sheet, null)

            val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
            val coinLogo = view.findViewById<ImageView>(R.id.coinLogo)
            val coinName = view.findViewById<TextView>(R.id.coinName)
            val coinPrice = view.findViewById<TextView>(R.id.coinPrice)
            val coinSymbol = view.findViewById<TextView>(R.id.coinSymbol)
            val coinCredentials = view.findViewById<TextView>(R.id.coinCredentials)
            val coinDesc = view.findViewById<TextView>(R.id.coinDescription)
            val coinRank = view.findViewById<TextView>(R.id.coinRank)

            coinName.text = coinDetail?.name
            coinSymbol.text = coinDetail?.symbol
            coinPrice.text =
                coinDetail?.statistics?.price?.let { it1 -> round(it1, 1000.0).toString() } + '$'
            coinRank.text = '#' + coin.rank.toString()
            coinDesc.text = coinDetail?.description


            val logoUrl: String =
                "https://s2.coinmarketcap.com/static/img/coins/128x128/" + coin.id + ".png"
            Picasso.get().load(logoUrl).into(coinLogo)

            if (coinDetail != null) {
                dialog.setCancelable(false)
                dialog.setContentView(view)
                dialog.show()
            } else {
                alert(
                    holder.itemView.context as Activity,
                    "Warning", "Please wait a few seconds."
                )
            }

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