package com.beyrak.crypto.views

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.beyrak.crypto.R

class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val marketLogo: ImageView = itemView.findViewById(R.id.marketLogo)
    val name: TextView = itemView.findViewById(R.id.name)
    val pair: TextView = itemView.findViewById(R.id.pair)
}