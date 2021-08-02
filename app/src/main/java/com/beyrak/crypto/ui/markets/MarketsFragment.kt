package com.beyrak.crypto.ui.markets

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beyrak.crypto.R

class MarketsFragment : Fragment() {

    companion object {
        fun newInstance() = MarketsFragment()
    }

    private lateinit var viewModel: MarketsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.markets_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MarketsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}