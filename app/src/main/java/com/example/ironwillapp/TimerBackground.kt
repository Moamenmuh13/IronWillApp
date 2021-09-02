package com.example.ironwillapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class TimerBackground : Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    private val timer = Timer()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent!!.getDoubleExtra(TimerBackground.TIME_EXTRA, 0.0)
        timer.scheduleAtFixedRate(TimeTask(time), 0, 1000)
        return START_STICKY

    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private inner class TimeTask(var time: Double) : java.util.TimerTask() {
        override fun run() {
            val intent = Intent(TIME_UPDATE)
            time++
            intent.putExtra(TIME_EXTRA, time)
            sendBroadcast(intent)
        }
    }

    companion object {
        const val TIME_UPDATE = "timerUpdate"
        const val TIME_EXTRA = "timerExtra"
    }
}




