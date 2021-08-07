package com.beyrak.crypto

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.databinding.ActivityMainBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.blockchain.general.GlobalMetrics
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt
import com.beyrak.crypto.R

import android.view.MenuInflater

import android.widget.Toast

import android.content.SharedPreferences
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.PopupMenu


class MainActivity : AppCompatActivity() {
    private val dataServiceCap = Config.retrofitCap.create(ApiService::class.java)

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
/*        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_markets,
                R.id.navigation_news
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)*/
        navView.setupWithNavController(navController)

        if (this.let { Constants.isOnline(it) })
            getData()
        else {
            alert(this, "Network Problem", "Please check your internet connection.")
        }

        binding.actions.setOnClickListener {
            setPopUpMenu()
        }
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
                    alert(this@MainActivity, "Error", "Support Us")
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
                    val text = "BTC Dominance: " + round(globalMetrics.btcDominance) + '%' +
                            ", ETH Dominance: " + round(globalMetrics.ethDominance) + '%' +
                            ", Active Crypto Currencies: " + globalMetrics.activeCryptoCurrencies +
                            ", Total Crypto Currencies: " + globalMetrics.totalCryptoCurrencies
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