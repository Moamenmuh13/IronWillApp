package com.example.ironwillapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ironwillapp.HelperMethods
import com.example.ironwillapp.R
import com.example.ironwillapp.ui.fragments.TimerFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFirstFragment()
    }

    private fun setupFirstFragment() {
        HelperMethods.changeFragmentBackStack(this, TimerFragment())
    }


}
