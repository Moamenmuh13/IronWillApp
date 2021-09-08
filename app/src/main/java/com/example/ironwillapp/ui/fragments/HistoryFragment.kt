package com.example.ironwillapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ironwillapp.R
import com.example.ironwillapp.adapters.HistoryAdapter
import com.example.ironwillapp.data.HistoryData
import com.example.ironwillapp.databinding.FragmentHistoryBinding
import com.example.ironwillapp.ui.activities.MainActivity

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_history, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSource = HistoryData().historyData()
        binding.recyclerView.adapter = HistoryAdapter(dataSource, activity as MainActivity)

    }

}