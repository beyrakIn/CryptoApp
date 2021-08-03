package com.beyrak.crypto.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beyrak.crypto.adapters.HomeAdapter
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config.Companion.retrofitMessari
import com.beyrak.crypto.databinding.FragmentHomeBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.messari.Data
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var job: Job? = null
    private val apiKey: String = "dfce78f5-66ec-42a4-8ed9-93db20aa7c93"

    //    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

//    lateinit var coinList: ArrayList<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
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

//        val dataService = retrofit.create(ApiService::class.java)
        val dataServiceMessari = retrofitMessari.create(ApiService::class.java)


/*        dataService.getMap(apiKey).enqueue(object : Callback<Result<Map>> {
            override fun onResponse(call: Call<Result<Map>>, response: Response<Result<Map>>) {
                binding.verticalRecyclerView.layoutManager = LinearLayoutManager(context)
                bindinghttps://data.messari.io/api/v1/.verticalRecyclerView.adapter = response.body()?.let { HomeAdapter(it.data) }

            }

            override fun onFailure(call: Call<Result<Map>>, t: Throwable) {
                println(t.message)
            }
        })*/

        try {
/*            dataService.getData(apiKey).enqueue(object : Callback<Result<List<Coin>>> {
                override fun onResponse(call: Call<Result<List<Coin>>>, response: Response<Result<List<Coin>>>) {
                    try {
                        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(context)
                        binding.verticalRecyclerView.adapter = response.body()?.let { HomeAdapter(it.data) }
                    } catch (e: Exception) {
                        println("Error: " + e.localizedMessage)
                    }
                }

                override fun onFailure(call: Call<Result<List<Coin>>>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })*/

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
                        Toast.makeText(context, response.errorBody()?.string(), Toast.LENGTH_SHORT)
                            .show()
                        println("Error: " + e.localizedMessage)
                    }
                    println(response.message())
                }

                override fun onFailure(call: Call<Result<List<Data>>>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    println(t.localizedMessage)
                }

            })
        } catch (e: Exception) {
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}