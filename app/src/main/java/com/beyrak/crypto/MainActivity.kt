package com.beyrak.crypto

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.databinding.ActivityMainBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.blockchain.general.GlobalMetrics
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private val dataServiceCap = Config.retrofitCap.create(ApiService::class.java)

    private var mRewardedAd: RewardedAd? = null

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        if (this.let { Constants.isOnline(it) })
            getData()
        else {
            alert(this, "Network Problem", "Please check your internet connection.")
        }

        binding.actions.setOnClickListener {
            setPopUpMenu()
        }

        setBanner()
    }

    private fun setBanner() {
        MobileAds.initialize(this) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun showAds() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            this,
            "ca-app-pub-2248916584991987/3927427402",
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("TAG", adError.message + "erorrrrrrrrrrrrrrrrr")
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    mRewardedAd = rewardedAd
                    alert(this@MainActivity, "Thanks!", "Thanks for support us.")
                }
            })
    }

    private fun setPopUpMenu() {
        val popupMenu = PopupMenu(this, binding.actions)
        popupMenu.menuInflater.inflate(R.menu.home_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.about_us -> {
                    alert(this@MainActivity, "Error", "About Us")
                }
                R.id.support_us -> {
                    showAds()
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun getData() {
        dataServiceCap.getGlobalMetrics().enqueue(object : Callback<Result<GlobalMetrics>> {
            override fun onResponse(
                call: Call<Result<GlobalMetrics>>,
                response: Response<Result<GlobalMetrics>>
            ) {
                if (response.isSuccessful) {
                    val globalMetrics: GlobalMetrics = response.body()!!.data
                    val text = "Dominance: BTC: " + round(globalMetrics.btcDominance) + '%' +
                            "  ETH: " + round(globalMetrics.ethDominance) + '%' +
                            "  Cryptos: " + globalMetrics.totalCryptoCurrencies +
                            "  Exchanges: " + globalMetrics.activeExchanges +
                            "  Market Cap: " + globalMetrics.quotes[0].totalMarketCap +
                            "  24h Vol: " + globalMetrics.quotes[0].totalVolume24H
                    binding.globalMetrics.text = text
                    binding.globalMetrics.isSelected = true
                } else {
                    alert(this@MainActivity, "Error", response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<Result<GlobalMetrics>>, t: Throwable) {
                alert(this@MainActivity, "Error", t.localizedMessage!!)
            }
        })
    }

    fun alert(parent: Activity, title: String, text: String) {
        Alerter.create(parent)
            .setTitle(title)
            .setText(text)
            .setBackgroundColorRes(R.color.teal_200)
            .show()
    }

    fun round(num: Double): Double {
        return ((num * 100.0).roundToInt() / 100.0)
    }
}