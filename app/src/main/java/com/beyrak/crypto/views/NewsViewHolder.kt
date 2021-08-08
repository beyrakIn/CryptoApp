package com.beyrak.crypto.views

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.beyrak.crypto.R

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val content: TextView = itemView.findViewById(R.id.content)
    val author: TextView = itemView.findViewById(R.id.author)
    val date: TextView = itemView.findViewById(R.id.date)
}