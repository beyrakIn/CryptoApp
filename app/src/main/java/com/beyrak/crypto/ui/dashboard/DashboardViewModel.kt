package com.beyrak.crypto.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyrak.crypto.enities.concretes.blockchain.Wallet

class DashboardViewModel : ViewModel() {

    private val wallet = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = wallet
    val address: LiveData<Wallet> = TODO()


}