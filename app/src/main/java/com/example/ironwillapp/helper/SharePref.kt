package com.example.ironwillapp.helper

import android.content.Context
import android.content.SharedPreferences

class SharePref(context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    private var _context: Context = context

    private val PREF_NAME = "MySharedPref"
    private val PRIVATE_MODE = 0
    private val IS_FIRST_TIME = "isFirstTime"


    private val USER_TIMER = "timer"

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

//    fun setFirstTimeLaunch(): Boolean {
//        return pref.getBoolean(IS_FIRST_TIME, true)
//    }
//
//    fun setUserTimer(timer: String) {
//        editor.putString(USER_TIMER, timer)
//        editor.apply()
//    }
//
//    fun getUserTimer(): String {
//        return pref.getString(USER_TIMER, "00:00:00").toString()
//    }

    private fun SharedPreferences.Editor.putDouble(key: String, double: Double) {
        putLong(key, java.lang.Double.doubleToRawLongBits(double))
    }

    private fun SharedPreferences.getDouble(key: String, default: Double) =
        java.lang.Double.longBitsToDouble(getLong(key,
            java.lang.Double.doubleToRawLongBits(default)))


    fun saveTimer(timer: Double) {
        editor.putDouble(USER_TIMER, timer)
        editor.apply()
    }

    fun getTimer(): Double {
        return pref.getDouble(USER_TIMER, 0.0)
    }
}