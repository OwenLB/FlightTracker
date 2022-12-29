package com.example.flighttracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlightListActivity : AppCompatActivity(), FlightListFragment.FlightListFragmentListener {
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

    override fun onCellClicked(flightModel: FlightModel) {
        Log.i("MainActivity", "onFlightClicked: $flightModel")

        val time = flightModel.lastSeen
        val icao24 = flightModel.icao24

        val intent = Intent(this, PathListActivity::class.java)

        intent.putExtra("TIME",time)
        intent.putExtra("ICAO24",icao24)

        startActivity(intent)
    }

}


