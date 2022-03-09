package com.example.ironwillapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ironwillapp.models.Timer

@Dao
interface TimerDao {

    @Insert()
    fun insertTimer(timer: Timer)

    @Delete
    fun delete(timer: Timer)


    @Query("select * from timer_table")
    fun getHistory(): MutableList<Timer>

    //
    @Query("SELECT * FROM timer_table WHERE daysCount LIKE :days ")
    fun getDays(days: Int): Int
}