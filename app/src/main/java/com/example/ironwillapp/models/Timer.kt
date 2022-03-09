package com.example.ironwillapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "timer_table")
class Timer constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    var daysCount: Int,
    @ColumnInfo
    var timerCount: Double
) {

}