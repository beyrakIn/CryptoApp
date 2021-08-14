package com.beyrak.crypto.ui.markets

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beyrak.crypto.Constants
import com.beyrak.crypto.R
import com.beyrak.crypto.adapters.MarketAdapter
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.databinding.MarketsFragmentBinding
import com.beyrak.crypto.enities.concretes.Result
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.beyrak.crypto.enities.concretes.coinmarketcap.Data

class MarketsFragment : Fragment() {
    val dataServiceCap = Config.retrofitCap.create(ApiService::class.java)

    private var _binding: MarketsFragmentBinding? = null

    private lateinit var viewModel: MarketsViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val root: View = inflater.inflate(R.layout.markets_fragment, container, false)
        _binding = MarketsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (context?.let { Constants.isOnline(it) } == true)
            getData()
        else {
            activity?.let { alert(it, "Network Problem", "Please check your internet connection.") }
        }


    }

    private fun getData() {
        try {
            dataServiceCap.getCapCoins().enqueue(object :
                Callback<Result<Data>> {
                override fun onResponse(
                    call: Call<Result<Data>>,
                    response: Response<Result<Data>>
                ) {
                    if (response.isSuccessful) {
                        binding.recyclerView.apply {
                            setItemViewCacheSize(50)
                            adapter =
                                response.body()?.let { MarketAdapter(it.data.exchangeMap.toList()) }
                        }
                        binding.progressBar.visibility = View.GONE
                    } else {
                        alert(activity!!, "Error", response.errorBody()!!.string())
                    }
                }

                override fun onFailure(
                    call: Call<Result<Data>>,
                    t: Throwable
                ) {
                    alert(activity!!, "Error", t.localizedMessage!!)
                }

            })
        } catch (e: Exception) {
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MarketsViewModel::class.java)
    }

    fun alert(parent: Activity, title: String, text: String) {
        Alerter.create(parent)
            .setTitle(title)
            .setText(text)
            .setBackgroundColorRes(R.color.purple_200)
            .show()
    }
}