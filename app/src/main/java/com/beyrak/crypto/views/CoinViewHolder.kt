package com.beyrak.crypto.views

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.beyrak.crypto.R
import com.robinhood.spark.SparkView

class CoinViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val coinSymbol: TextView = view.findViewById(R.id.coinSymbol)
    val coinPrice: TextView = view.findViewById(R.id.coinPrice)
    val coinPriceProgress: ProgressBar = view.findViewById(R.id.progressBar)
    val coinLogo: ImageView = view.findViewById(R.id.coinLogo)
    val sparkView: ImageView = view.findViewById(R.id.sparkView)
    val percent: TextView = view.findViewById(R.id.percent)
    val coinRank: TextView = view.findViewById(R.id.coinRank)
}