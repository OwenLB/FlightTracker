package com.example.flighttracker

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val airportsList = Utils.generateAirportList()
        setContentView(R.layout.activity_main)
        title = "Flight Tracker"
        spinner = findViewById(R.id.spinnerAirports)
        val formatedAirportsList: MutableList<String?> = ArrayList()
        for (item: Airport in airportsList) {
            formatedAirportsList.add(item.getFormattedName());
        }

        val arrayAdapter: ArrayAdapter<String?> = ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, formatedAirportsList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (parent.getItemAtPosition(position) == "Choose Airport from list") {
                }
                else {
                    val item = parent.getItemAtPosition(position).toString()
                    Toast.makeText(parent.context, "Selected: $item", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    }
}