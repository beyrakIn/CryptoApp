package com.beyrak.crypto.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.beyrak.crypto.enities.concretes.Coin
import com.beyrak.crypto.enities.concretes.Result
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    val coin: MutableLiveData<Response<Result<Coin>>> = MutableLiveData()

    fun getData() {
    }
}