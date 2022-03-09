package com.example.ironwillapp.helper

import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class FormatTimeToString {

    companion object {
        var numOfDays: Int = 0
        var hours: Int = 0
    }

    fun formatTimeToDouble(time: Double): String {
        val rounded = time.roundToInt()
        val sec = ((rounded % 86400) % 3600) % 60
        val min = ((rounded % 86400) % 3600) / 60
        hours = (rounded % 86400) / 3600
        numOfDays = TimeUnit.SECONDS.toDays(time.roundToLong()).toInt()
        return formatTimeToString(sec, min, hours)
    }

    private fun formatTimeToString(seconds: Int, minutes: Int, hours: Int): String {
        return String.format("%02d", hours) + ":" + String.format(
            "%02d",
            minutes
        ) + ":" + String.format("%02d", seconds)
    }
}