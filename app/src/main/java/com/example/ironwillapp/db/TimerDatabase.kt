package com.example.ironwillapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ironwillapp.TimerDao
import com.example.ironwillapp.models.Timer


@Database(entities = [Timer::class], version = 1, exportSchema = false)
abstract class TimerDatabase : RoomDatabase() {
    abstract fun timerDao(): TimerDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TimerDatabase? = null
        private val DB_NAME: String = "TimerTable"

        fun getInstance(context: Context): TimerDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: getDatabase(context).also { INSTANCE = it }
            }
        }

        private fun getDatabase(context: Context): TimerDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TimerDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}