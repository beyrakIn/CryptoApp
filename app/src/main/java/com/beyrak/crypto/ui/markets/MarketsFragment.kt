package com.beyrak.crypto.ui.markets

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beyrak.crypto.R
import com.beyrak.crypto.adapters.HomeAdapter
import com.beyrak.crypto.adapters.MarketAdapter
import com.beyrak.crypto.adapters.NewsAdapter
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.databinding.FragmentNewsBinding
import com.beyrak.crypto.databinding.MarketsFragmentBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.messari.Data
import com.beyrak.crypto.enities.concretes.messari.Market
import com.beyrak.crypto.enities.concretes.messari.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketsFragment : Fragment() {

    private val dataServiceMessari = Config.retrofitMessari.create(ApiService::class.java)

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataServiceMessari.getMarkets().enqueue(object : Callback<Result<List<Market>>> {
            override fun onResponse(
                call: Call<Result<List<Market>>>,
                response: Response<Result<List<Market>>>
            ) {
                if (response.isSuccessful) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                    binding.recyclerView.adapter =
                        response.body()?.let { MarketAdapter(it.data) }
                } else {

                }
            }

            override fun onFailure(call: Call<Result<List<Market>>>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                println(t.localizedMessage + "errorrr")
            }


        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MarketsViewModel::class.java)
    }

}