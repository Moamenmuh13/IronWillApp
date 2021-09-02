package com.example.ironwillapp.ui.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ironwillapp.HelperMethods
import com.example.ironwillapp.R
import com.example.ironwillapp.TimerBackground
import com.example.ironwillapp.databinding.FragmentTimerBinding
import com.example.ironwillapp.ui.activities.MainActivity
import java.util.*
import kotlin.math.roundToInt

class TimerFragment : Fragment(), View.OnClickListener {


    private lateinit var timer: Timer
    private val TAG = "TIMER_FRAGMENT"
    private lateinit var counter: CountDownTimer

    private var timeStarted = false
    private lateinit var serviceIntent: Intent
    private lateinit var binding: FragmentTimerBinding

    private var TIME: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceIntent = Intent(context, TimerBackground::class.java)
        context?.registerReceiver(updateTime, IntentFilter(TimerBackground.TIME_UPDATE))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_timer, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)


    }

    fun updateProgressBarLength(progressLength: Int) {
        if (binding.progressBar.progress == 24) {
            binding.days.text
            binding.progressBar.progress = 0
        } else
            binding.progressBar.progress = progressLength
    }

    private fun initViews(view: View) {
        binding.startBtn.setOnClickListener(this)
        binding.pauseBtn.setOnClickListener(this)
        binding.rankBtn.setOnClickListener(this)
        binding.historyBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.startBtn -> {
                startTimer()
                binding.startBtn.isEnabled = false
                binding.pauseBtn.isEnabled = true
                binding.timer.visibility = View.VISIBLE
                binding.days.visibility = View.VISIBLE
            }
            binding.pauseBtn -> {
                stopTimer()
                binding.startBtn.isEnabled = true
                binding.pauseBtn.isEnabled = false
            }

            binding.rankBtn -> {
                HelperMethods.changeFragment(activity as MainActivity, RankFragment())
            }

            binding.historyBtn -> {
                HelperMethods.changeFragment(activity as MainActivity, HistoryFragment())
            }
        }
    }

    private fun stopTimer() {
        context?.stopService(serviceIntent)
        serviceIntent.putExtra(TimerBackground.TIME_EXTRA, TIME)
//        timeStarted = true
    }


    private fun startTimer() {
        serviceIntent.putExtra(TimerBackground.TIME_EXTRA, TIME)
        context?.startService(serviceIntent)
//        timeStarted = true

    }


    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            TIME = p1!!.getDoubleExtra(TimerBackground.TIME_EXTRA, 0.0)
            binding.timer.text = getTimeStringFromDouble(TIME)
        }

        private fun getTimeStringFromDouble(time: Double): String {
            val rounded = time.roundToInt()
            val sec = rounded % 86400 % 3600 % 60
            val min = rounded % 86400 % 3600 / 60
            val hours = rounded % 86400 / 3600
            updateProgressBarLength(hours)

            return formatTime(sec, min, hours)
        }

        private fun formatTime(seconds: Int, minutes: Int, hours: Int): String {
            return String.format("%02d", hours) + ":" + String.format(
                "%02d",
                minutes
            ) + ":" + String.format("%02d", seconds)
        }
    }
}