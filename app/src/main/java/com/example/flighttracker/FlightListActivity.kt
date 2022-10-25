package com.example.flighttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlightListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        val begin = intent.getLongExtra("BEGIN", 0)
        val end = intent.getLongExtra("END", 0)
        val isArrival = intent.getBooleanExtra("IS_ARRIVAL", false)
        val icao = intent.getStringExtra("ICAO")
        val viewModel = ViewModelProvider(this).get(FlightListViewModel::class.java)


        Log.i("MainActivity", "begin: $begin, " +
                "end: $end, " +
                "icao: $icao, " +
                "isArrival: $isArrival")

        viewModel.doRequest(begin, end, isArrival, icao!!)

        //viewModel.getFlightListLiveData().observe(this, Observer {
            //findViewById<TextView>(R.id.textView).text = it.toString()

            //Récupérer le recyclerview
            //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

            //Attacher un adapter
            //recyclerView.adapter = FlightListAdapter(it, this)

            //Attacher un layout manager à la recyclerview
            //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //})
    }

}


