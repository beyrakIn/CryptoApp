package com.beyrak.crypto.ui.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config.Companion.retrofitBlockchain
import com.beyrak.crypto.databinding.FragmentDashboardBinding
import com.beyrak.crypto.enities.concretes.blockchain.Wallet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    val dataService = retrofitBlockchain.create(ApiService::class.java)


    // This property is only valid between onCreateView and
    // onDestroyView.
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

        binding.submit.setOnClickListener {
            dataService.getWallet(binding.walletAddress.editText?.text.toString())
                .enqueue(object : Callback<Wallet> {
                    override fun onResponse(call: Call<Wallet>, response: Response<Wallet>) {
                        if (response.isSuccessful){
                            val wallet: Wallet? = response.body()
                            binding.walletInfo.text = wallet?.hash160 +
                                    "\n" + "Total balance: " + wallet?.final_balance + " SATOSHI"
                        }else{
                            Toast.makeText(context, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Wallet>, t: Throwable) {
                        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                    }

                })
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}