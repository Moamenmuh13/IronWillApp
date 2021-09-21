package com.example.ironwillapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "timer_table")
class Timer constructor(id: Int, daysCount: Int, timerCount: Double) {


    constructor() : this(0, 0, 0.0)

    @PrimaryKey
    var id = id

    @ColumnInfo
    var timerCount = timerCount
        get() = field
        set(value) {
            field = value
        }

    @ColumnInfo
    var daysCount = daysCount
        get() = field
        set(value) {
            field = value
        }
}