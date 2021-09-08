package com.example.ironwillapp.ui.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ironwillapp.HelperMethods
import com.example.ironwillapp.R
import com.example.ironwillapp.TimerService
import com.example.ironwillapp.databinding.FragmentTimerBinding
import com.example.ironwillapp.ui.activities.MainActivity
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class TimerFragment : Fragment(), View.OnClickListener {

    private var timeStarted: Boolean = false
    private val TAG = "TimerFragment"
    private val SAVE_TIME = "SAVE TIME"
    private val SAVE_DAYS = "SAVE DAYS"


    private lateinit var timer: Timer
    private lateinit var serviceIntent: Intent
    private lateinit var binding: FragmentTimerBinding

    private var TIME: Double = 0.0
    private var daysCount: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceIntent = Intent(context, TimerService::class.java)
        context?.registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
//        val serviceTime = activity.intent.getDoubleExtra()
        startTimer()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(SAVE_TIME, TIME)
        outState.putLong(SAVE_DAYS, daysCount)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_timer, container, false
        )
        if (savedInstanceState != null) {
            TIME = savedInstanceState.getDouble(SAVE_TIME);
            daysCount = savedInstanceState.getLong(SAVE_DAYS);
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }


    private fun initViews() {
        timer = Timer()
        binding.startBtn.setOnClickListener(this)
        binding.resetBtn.setOnClickListener(this)
        binding.rankBtn.setOnClickListener(this)
        binding.historyBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.startBtn -> {
            }

            binding.resetBtn -> {
                resetTimer()
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
        timeStarted = true
        context?.startService(serviceIntent)
        serviceIntent.putExtra(TimerService.TIME_EXTRA, TIME)

    }


    private fun stopTimer() {
        timeStarted = false
        context?.stopService(serviceIntent)
        serviceIntent.putExtra(TimerService.TIME_EXTRA, TIME)
    }

    private fun resetTimer() {
        val resetAlert = AlertDialog.Builder(activity as MainActivity)
            .setTitle("Reset Timer")
            .setMessage("Are you sure you want to reset the timer")
            .setPositiveButton(
                "Reset"
            ) { _, _ ->
                binding.startBtn.isEnabled = true

            }
            .setNegativeButton(
                "Cancel"
            ) { _, _ ->
                Toast.makeText(activity, "okay", Toast.LENGTH_SHORT).show()
            }

        resetAlert.show()
//        context?.stopService(serviceIntent)

    }


    fun updateProgressBarLength(progressLength: Int) {
        if (binding.progressBar.progress == 24) {
            binding.progressBar.progress = 0
        } else
            binding.progressBar.progress = progressLength
    }


    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            TIME = p1!!.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.timer.text = getTimeStringFromDouble(TIME)
        }

        private fun getTimeStringFromDouble(time: Double): String {
            val rounded = time.roundToInt()
            val sec = ((rounded % 86400) % 3600) % 60
            val min = ((rounded % 86400) % 3600) / 60
            val hours = (rounded % 86400) / 3600
            daysCount = TimeUnit.SECONDS.toDays(time.roundToLong())

            binding.days.text = "$daysCount Days"
            updateProgressBarLength(hours)
            return formatTime(sec, min, hours)
        }
    }

    private fun formatTime(seconds: Int, minutes: Int, hours: Int): String {
        return String.format("%02d", hours) + ":" + String.format(
            "%02d",
            minutes
        ) + ":" + String.format("%02d", seconds)
    }
}