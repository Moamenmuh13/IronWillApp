package com.example.ironwillapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ironwillapp.R
import com.example.ironwillapp.databinding.TicketHistoryBinding
import com.example.ironwillapp.models.History

class HistoryAdapter(private val dataSet: MutableList<History>, val context: Context) :
    RecyclerView.Adapter<HistoryAdapter.myViewModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewModel {

        val binding: TicketHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.ticket_history, parent, false
        )
        return myViewModel(binding)

    }

    override fun onBindViewHolder(holder: myViewModel, position: Int) {
        val item = dataSet[position]
        with(holder) {
            binding.historyDays.text = item.days
            binding.startedDate.text = item.startedDate
            binding.endedDate.text = item.endedDate
            binding.reason.text = item.reason
        }
    }

    override fun getItemCount(): Int = dataSet.size

    class myViewModel(val binding: TicketHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}