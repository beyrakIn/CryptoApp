package com.beyrak.crypto.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.beyrak.crypto.R
import com.beyrak.crypto.enities.concretes.coinmarketcap.ExchangeMap
import com.beyrak.crypto.views.MarketViewHolder
import com.squareup.picasso.Picasso

class MarketAdapter(private val marketList: List<ExchangeMap>) :
    RecyclerView.Adapter<MarketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MarketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.market_card,
            parent, false
        )
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val market: ExchangeMap = marketList[position]
        holder.name.text = market.name
        holder.pair.text = market.first_historical_data

        val url = "https://s2.coinmarketcap.com/static/img/exchanges/128x128/" + market.id + ".png"

        Picasso.get().load(url).into(holder.marketLogo)
    }

    override fun getItemCount(): Int {
        return marketList.size
    }
}