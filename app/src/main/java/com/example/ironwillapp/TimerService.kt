package com.example.ironwillapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*


class TimerService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    private val timer = Timer()
    private var TIMER_VALUE = 0.0
    private var DELAY: Long = 0
    private var MILE_SECONDE: Long = 1000


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getDoubleExtra(TIME_EXTRA, TIMER_VALUE)
        timer.scheduleAtFixedRate(TimeTask(time), DELAY, MILE_SECONDE)
        return START_STICKY
    }


    private inner class TimeTask(private var time: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIME_EXTRA, time)
            sendBroadcast(intent)
        }

    }

    companion object {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

}