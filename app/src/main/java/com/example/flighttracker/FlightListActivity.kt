package com.example.flighttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class FlightListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        val begin = intent.getLongExtra("BEGIN", 0)
        val end = intent.getLongExtra("END", 0)
        val isArrival = intent.getBooleanExtra("IS_ARRIVAL", false)
        val icao = intent.getStringExtra("ICAO")


        Log.i("MainActivity", "begin: $begin, " +
                "end: $end, " +
                "icao: $icao, " +
                "isArrival: $isArrival")
    }
}


