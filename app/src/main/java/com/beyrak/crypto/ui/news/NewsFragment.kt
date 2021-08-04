package com.beyrak.crypto.ui.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beyrak.crypto.adapters.NewsAdapter
import com.beyrak.crypto.api.ApiService
import com.beyrak.crypto.api.Config
import com.beyrak.crypto.databinding.FragmentNewsBinding
import com.beyrak.crypto.enities.concretes.Result
import com.beyrak.crypto.enities.concretes.messari.News
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataServiceMessari.getNews().enqueue(object : Callback<Result<List<News>>> {
            override fun onResponse(
                call: Call<Result<List<News>>>,
                response: Response<Result<List<News>>>
            ) {
                if (response.isSuccessful) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                    binding.recyclerView.adapter =
                        response.body()?.let { NewsAdapter(it.data) }
                } else {

                }
            }

            override fun onFailure(call: Call<Result<List<News>>>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                println(t.localizedMessage + "errorrr")
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}