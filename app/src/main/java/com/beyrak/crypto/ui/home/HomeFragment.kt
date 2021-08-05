package com.beyrak.crypto.ui.home

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beyrak.crypto.R
import com.beyrak.crypto.adapters.HomeAdapter
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config.Companion.retrofitCal
import com.beyrak.crypto.api.Config.Companion.retrofitMessari
import com.beyrak.crypto.databinding.FragmentHomeBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.messari.Data
import com.tapadoo.alerter.Alerter
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataServiceMessari = retrofitMessari.create(ApiService::class.java)
        val dataServiceCal = retrofitCal.create(ApiService::class.java)


        try {
            dataServiceMessari.getCoins().enqueue(object : Callback<Result<List<Data>>> {
                override fun onResponse(
                    call: Call<Result<List<Data>>>,
                    response: Response<Result<List<Data>>>
                ) {
                    try {
                        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(context)
                        binding.verticalRecyclerView.adapter =
                            response.body()?.let { HomeAdapter(it.data) }
                    } catch (e: Exception) {
                        activity?.let { it1 ->
                            Alerter.create(it1)
                                .setTitle("Alert Title")
                                .setText(e.localizedMessage)
                                .setBackgroundColorRes(R.color.purple_200)
                                .show()
                        }
                    }
                    println(response.message())
                }

                override fun onFailure(call: Call<Result<List<Data>>>, t: Throwable) {
                    activity?.let { it1 ->
                        Alerter.create(it1)
                            .setTitle("Alert Title")
                            .setText(t.localizedMessage)
                            .setBackgroundColorRes(R.color.purple_200)
                            .show()
                    }
                }

            })
            /*dataServiceCal.getCalCoins("FJMmKkvWUO7i0UrwqVhPxdEgXQTmw8Uaiy3Ry882")
                .enqueue(object :
                    Callback<com.beyrak.crypto.enities.concretes.coinmarketcal.Response> {
                    override fun onResponse(
                        call: Call<com.beyrak.crypto.enities.concretes.coinmarketcal.Response>,
                        response: Response<com.beyrak.crypto.enities.concretes.coinmarketcal.Response>
                    ) {
                        if (response.isSuccessful){

                        }else{
                            response.errorBody()?.let {
                                alert(context as Activity, "Error",
                                    it.string())
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<com.beyrak.crypto.enities.concretes.coinmarketcal.Response>,
                        t: Throwable
                    ) {
                        alert(context as Activity, "Error", t.localizedMessage)
                    }

                })*/
        } catch (e: Exception) {
            activity?.let { it1 ->
                Alerter.create(it1)
                    .setTitle("Alert Title")
                    .setText(e.localizedMessage)
                    .setBackgroundColorRes(R.color.purple_200)
                    .show()
            }
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