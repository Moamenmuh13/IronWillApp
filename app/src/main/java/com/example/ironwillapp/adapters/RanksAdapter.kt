package com.example.ironwillapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ironwillapp.R
import com.example.ironwillapp.databinding.TicketRanksBinding
import com.example.ironwillapp.models.Ranks

class RanksAdapter(private var dataSet: MutableList<Ranks>, private val context: Context) :
    RecyclerView.Adapter<RanksAdapter.myViewHolder>() {

    inner class myViewHolder(val binding: TicketRanksBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding: TicketRanksBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.ticket_ranks, parent, false
        )
        return myViewHolder(binding)
    }


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val item = dataSet[position]
        with(holder) {
            with(item) {
                binding.rank.text = item.rankLvl
                binding.numOfDays.text = item.numOfDays
                binding.rankView.setOnClickListener {
                    Toast.makeText(context, item.rankLvl, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun getItemCount(): Int = dataSet.size


}