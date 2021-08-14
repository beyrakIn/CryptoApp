package com.beyrak.crypto.ui.home

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.opengl.Visibility
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
import com.beyrak.crypto.adapters.HomeAdapter
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config.Companion.retrofitCap
import com.beyrak.crypto.api.Config.Companion.retrofitMessari
import com.beyrak.crypto.databinding.FragmentHomeBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.coinmarketcap.Data
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val dataServiceCap: ApiService = retrofitCap.create(ApiService::class.java)

    //    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//            ViewModelProvider(
//                this,
//                ViewModelProvider.NewInstanceFactory()
//            ).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


////        val textView: TextView = binding.textHome
//        homeViewModel.data.observe(viewLifecycleOwner, Observer {
////            textView.text = it
//        })
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
                    call: Call<Result<Data>>, response: Response<Result<Data>>
                ) {
                    if (response.isSuccessful) {
                        val data: Data = response.body()!!.data
                        val adapter = HomeAdapter(data.cryptoCurrencyMap.toList())
                        try {
                            binding.verticalRecyclerView.apply {
                                setHasFixedSize(true)
                                setItemViewCacheSize(500)
                                this.adapter = adapter
                            }
                            binding.progressBar.visibility = View.GONE
                        } catch (e: Exception) {
                            println(e.message + " errrrorrr")
                        }
                    } else {
                        alert(activity!!, "Error", response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<Result<Data>>, t: Throwable) {
                    activity?.let { alert(it, "Error", t.localizedMessage!!) }
                }

            })
        } catch (e: Exception) {
        }
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