package com.beyrak.crypto.adapters

import com.robinhood.spark.SparkAdapter

class SparkViewAdapter(array: List<Float>) : SparkAdapter() {
    private val data = array


    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(index: Int): Any {
        return data[index]
    }

    override fun getY(index: Int): Float {
        return data[index]
    }
}