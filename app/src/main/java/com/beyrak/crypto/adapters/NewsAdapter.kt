package com.beyrak.crypto.adapters

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.beyrak.crypto.R
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.enities.concretes.messari.News
import com.beyrak.crypto.views.CoinViewHolder
import com.beyrak.crypto.views.NewsViewHolder
import org.jsoup.Jsoup

class NewsAdapter(private val newsList: List<News>) : RecyclerView.Adapter<NewsViewHolder>() {
    private val dataServiceMessari = Config.retrofitMessari.create(ApiService::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.news_card,
            parent, false
        )
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        holder.title.text = news.title
        holder.content.text = Jsoup.parse(news.content).text()
        holder.author.text = news.author.name
        holder.date.text = news.published_at


        holder.itemView.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(holder.itemView.context)

            dialogBuilder.setMessage(Jsoup.parse(news.content).text())
                .setCancelable(false)
                .setNegativeButton("Ok") { dialog, id ->
                    dialog.cancel()
                }

            val alert = dialogBuilder.create()
            alert.setTitle(news.title)
            alert.show()
        }


    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}