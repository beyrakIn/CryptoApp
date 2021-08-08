package com.beyrak.crypto.ui.news

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beyrak.crypto.Constants
import com.beyrak.crypto.R
import com.beyrak.crypto.adapters.HomeAdapter
import com.beyrak.crypto.adapters.NewsAdapter
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.databinding.FragmentNewsBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.messari.News
import com.tapadoo.alerter.Alerter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private var _binding: FragmentNewsBinding? = null

    private val dataServiceMessari = Config.retrofitMessari.create(ApiService::class.java)


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*      notificationsViewModel =
                  ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                      NotificationsViewModel::class.java
                  )*/

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

/*        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (context?.let { Constants.isOnline(it) } == true)
            getData()
        else {
            activity?.let { alert(it, "Network Problem", "Please check your internet connection.") }
            Handler().postDelayed({ getData() }, 5000)
        }

    }

    private fun getData() {
        dataServiceMessari.getNews(100).enqueue(object : Callback<Result<List<News>>> {
            override fun onResponse(
                call: Call<Result<List<News>>>,
                response: Response<Result<List<News>>>
            ) {
                if (response.isSuccessful) {
                    try {
                        binding.recyclerView.adapter =
                            response.body()?.let { NewsAdapter(it.data) }
                        binding.progressBar.visibility = View.GONE
                    } catch (e: Exception) {
                        activity?.let { it1 -> alert(it1, "Error", e.localizedMessage!!) }
                    }
                } else {
                    activity?.let { it1 ->
                        response.errorBody()?.let { alert(it1, "Error", it.string()) }
                    }
                    Handler().postDelayed({ getData() }, 5000)
                }
            }

            override fun onFailure(call: Call<Result<List<News>>>, t: Throwable) {
                activity?.let { it1 -> alert(it1, "Error", t.localizedMessage!!) }
                Handler().postDelayed({ getData() }, 5000)
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