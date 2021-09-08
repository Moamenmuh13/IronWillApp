package com.example.ironwillapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ironwillapp.R
import com.example.ironwillapp.adapters.RanksAdapter
import com.example.ironwillapp.data.RanksData
import com.example.ironwillapp.databinding.FragmentMyRankBinding
import com.example.ironwillapp.ui.activities.MainActivity


class RankFragment : Fragment() {
    private lateinit var binding: FragmentMyRankBinding

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_my_rank, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        val dataSource = RanksData().ranksData()

        binding.recyclerView.adapter = RanksAdapter(dataSource, activity as MainActivity)
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.myRankRecyclerView)

    }

}