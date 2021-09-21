package com.example.ironwillapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.ironwillapp.models.Timer

@Dao
interface TimerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTimer(timer: Timer)

    @Delete
    fun delete(timer: Timer)


//
//    @Query("SELECT * FROM timer_table WHERE daysCount LIKE :days ")
//    fun getDays(days: Int): Timer
}