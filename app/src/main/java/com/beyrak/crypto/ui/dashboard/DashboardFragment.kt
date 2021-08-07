package com.beyrak.crypto.ui.dashboard

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beyrak.crypto.Constants
import com.beyrak.crypto.R
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config.Companion.retrofitBlockchain
import com.beyrak.crypto.databinding.FragmentDashboardBinding
import com.beyrak.crypto.enities.concretes.blockchain.Wallet
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val dataService: ApiService = retrofitBlockchain.create(ApiService::class.java)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        dashboardViewModel =
//            ViewModelProvider(
//                this,
//                ViewModelProvider.NewInstanceFactory()
//            ).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            if (!binding.walletAddress.editText?.text.isNullOrEmpty()) {
                if (context?.let { Constants.isOnline(it) } == true)
                    getData()
                else {
                    activity?.let { alert(it, "Network Problem", "Please check your internet connection.") }
                }
            } else {
                alert(activity!!, "Error", "Please enter wallet address")
            }
        }
    }

    private fun getData() {
        dataService.getWallet(binding.walletAddress.editText?.text.toString())
            .enqueue(object : Callback<Wallet> {
                override fun onResponse(call: Call<Wallet>, response: Response<Wallet>) {
                    if (response.isSuccessful) {
                        val wallet: Wallet? = response.body()
                        binding.walletInfo.text = "Hash\n" + wallet?.hash160
                        binding.totalReceived.text =
                            "Total Received\n" + wallet?.total_received.toString()
                        binding.totalSent.text =
                            "Total Sent\n" + wallet?.total_sent.toString()
                        binding.finalBalance.text =
                            "Final Balance\n" + wallet?.final_balance.toString()
                    } else {
                        alert(
                            activity!!, "Error", response.errorBody()!!.string()
                        )
                    }
                }

                override fun onFailure(call: Call<Wallet>, t: Throwable) {
//                        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                    alert(activity!!, "Error", t.localizedMessage!!)
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun alert(parent: Activity, title: String, text: String) {
        Alerter.create(parent)
            .setTitle(title)
            .setText(text)
            .setBackgroundColorRes(R.color.purple_200)
            .show()
    }
}