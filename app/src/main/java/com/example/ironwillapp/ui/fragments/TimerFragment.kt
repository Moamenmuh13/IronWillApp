package com.example.ironwillapp.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ironwillapp.R
import com.example.ironwillapp.TimerService
import com.example.ironwillapp.databinding.FragmentTimerBinding
import com.example.ironwillapp.helper.FormatTimeToString
import com.example.ironwillapp.helper.SharePref
import com.example.ironwillapp.ui.activities.MainActivity


class TimerFragment : Fragment(), View.OnClickListener {

    private val TAG = "TimerFragment"


    private var _binding: FragmentTimerBinding? = null
    private lateinit var sharePref: SharePref
    private val binding get() = _binding!!

    private lateinit var serviceIntent: Intent
    private var timerStarted: Boolean = false
    private var TIME = 0.0
    private var PROGRESSBAR_LENGTH = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: 1")
        serviceIntent = Intent(context, TimerService::class.java)
        context?.registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        sharePref = SharePref(requireContext())

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d(TAG, "onCreateView: 2")

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_timer, container, false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: 3")
        initViews()
        checkIfTimerRunning()
    }


    private fun initViews() {
        binding.startBtn.setOnClickListener(this)
        binding.resetBtn.setOnClickListener(this)
        binding.rankBtn.setOnClickListener(this)
        binding.historyBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.startBtn -> {
                checkIfTimerRunning()
            }

            binding.resetBtn -> {
                showAlertDialog()
            }

            binding.rankBtn -> {
                findNavController().navigate(R.id.action_timerFragment_to_rankFragment)
            }

            binding.historyBtn -> {
                findNavController().navigate(R.id.action_timerFragment_to_historyFragment)
            }
        }
    }


    private fun startTimer() {
        timerStarted = true
        context?.startService(serviceIntent)
        serviceIntent.putExtra(TimerService.TIME_EXTRA, TIME)
    }

    private fun showAlertDialog() {
        val resetAlert = AlertDialog.Builder(activity as MainActivity)
            .setTitle("Reset Timer")
            .setMessage("Are you sure you want to reset the timer")
            .setPositiveButton(
                "Reset"
            ) { _, _ ->
                resetTimer()
            }
            .setNegativeButton(
                "Cancel"
            ) { _, _ ->
            }
        resetAlert.show()
    }

    private fun resetTimer() {
        if (timerStarted) {
            stopTimerService()
        } else {
            Toast.makeText(context, "Timer already stopped", Toast.LENGTH_SHORT).show()
        }
    }


    private fun stopTimerService() {
        TIME = 0.0
        timerStarted = false
        context?.stopService(serviceIntent)

        binding.time.text = FormatTimeToString().formatTimeToDouble(TIME)

        updateProgressBarLength(PROGRESSBAR_LENGTH)
        setNumOfDays()

        restartTimer()
    }

    private fun saveTime() {
        if (timerStarted) {
            sharePref.saveTimer(TIME)
            Toast.makeText(requireContext(), "Timer is saved suc", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Timer is not running", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkIfTimerRunning() {
        if (!timerStarted) {
            startTimer()
        } else {
            Toast.makeText(context, "Timer already running", Toast.LENGTH_SHORT).show()
        }
    }

    private fun restartTimer() {
        TimerService().onDestroy()
        checkIfTimerRunning()
    }


    private fun updateProgressBarLength(progressLength: Int) {
        if (binding.progressBar.progress == 24) {
            binding.progressBar.progress = PROGRESSBAR_LENGTH
        } else
            binding.progressBar.progress = progressLength
    }


    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (timerStarted) {
                TIME = p1!!.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
                binding.time.text = FormatTimeToString().formatTimeToDouble(TIME)
                setNumOfDays()
                updateProgressBarLength(FormatTimeToString.hours)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setNumOfDays() = when (FormatTimeToString.numOfDays) {
        0, 1 -> binding.days.text = FormatTimeToString.numOfDays.toString() + " Day"
        else -> binding.days.text = FormatTimeToString.numOfDays.toString() + " Days"
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        saveTime()
        Log.d(TAG, "onDestroyView: 4")

    }

}