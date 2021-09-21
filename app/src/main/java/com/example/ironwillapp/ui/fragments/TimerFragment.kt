package com.example.ironwillapp.ui.fragments

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ironwillapp.HelperMethods
import com.example.ironwillapp.R
import com.example.ironwillapp.TimerService
import com.example.ironwillapp.databinding.FragmentTimerBinding
import com.example.ironwillapp.models.Timer
import com.example.ironwillapp.ui.activities.MainActivity
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class TimerFragment : Fragment(), View.OnClickListener {

    private val TAG = "TimerFragment"
    private val SAVE_TIME = "SAVE TIME"
    private val SAVE_DAYS = "SAVE DAYS"


    private lateinit var serviceIntent: Intent
    private lateinit var binding: FragmentTimerBinding
    private var TIME = 0.0
    private var numOfDays: Int = 0
    private lateinit var timer: Timer
    private var running: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceIntent = Intent(context, TimerService::class.java)
        context?.registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))

        timer = Timer()
        if (savedInstanceState != null) {
            TIME = savedInstanceState.getDouble(SAVE_TIME);
            numOfDays = savedInstanceState.getInt(SAVE_DAYS);
        }


        startTimer()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(SAVE_TIME, TIME)
        outState.putInt(SAVE_DAYS, numOfDays)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_timer, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
//        binding.start.setOnClickListener(this)
//        binding.startBtn.setOnClickListener(this)
//        binding.resetBtn.setOnClickListener(this)
        binding.rankBtn.setOnClickListener(this)
        binding.historyBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.start -> {
//                startTimer()
                resetTimer()
                binding.start.visibility = GONE
            }
            binding.startBtn -> {
            }

            binding.resetBtn -> {
//                resetTimer()
            }

            binding.rankBtn -> {
                HelperMethods.changeFragmentBackStack(activity as MainActivity, RankFragment())
            }

            binding.historyBtn -> {
                HelperMethods.changeFragmentBackStack(activity as MainActivity, HistoryFragment())
            }
        }
    }


    private fun startTimer() {
        running = true
        context?.startService(serviceIntent)
        serviceIntent.putExtra(TimerService.TIME_EXTRA, TIME)
//        val timerDB = TimerDatabase.getInstance(activity as MainActivity)
//        timerDB.timerDao().insertTimer(Timer(1, numOfDays.toInt(), TIME.toInt()))

    }


    private fun resetTimer() {

//        sleep(5000)
//        startTimer()


        val resetAlert = AlertDialog.Builder(activity as MainActivity)
            .setTitle("Reset Timer")
            .setMessage("Are you sure you want to reset the timer")
            .setPositiveButton(
                "Reset"
            ) { _, _ ->
                running = false
                context?.stopService(serviceIntent)
                TIME = 0.0
                binding.timer.text = getTimeStringFromDouble(TIME)
            }
            .setNegativeButton(
                "Cancel"
            ) { _, _ ->
            }

        resetAlert.show()
        context?.stopService(serviceIntent)

    }


    private fun updateProgressBarLength(progressLength: Int) {
        if (binding.progressBar.progress == 24) {
            binding.progressBar.progress = 0
        } else
            binding.progressBar.progress = progressLength
    }


    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (running) {
                TIME = p1!!.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
                binding.timer.text = getTimeStringFromDouble(TIME)
            }

        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val rounded = time.roundToInt()
        val sec = ((rounded % 86400) % 3600) % 60
        val min = ((rounded % 86400) % 3600) / 60
        val hours = (rounded % 86400) / 3600
        numOfDays = TimeUnit.SECONDS.toDays(time.roundToLong()).toInt()
        binding.days.text = "$numOfDays Days"
        updateProgressBarLength(hours)
        return formatTime(sec, min, hours)
    }

    private fun formatTime(seconds: Int, minutes: Int, hours: Int): String {
        return String.format("%02d", hours) + ":" + String.format(
            "%02d",
            minutes
        ) + ":" + String.format("%02d", seconds)
    }

//
//    override fun onDestroy() {
//        super.onDestroy()
//        stopTimer()
//    }
}