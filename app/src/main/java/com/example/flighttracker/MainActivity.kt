package com.example.flighttracker

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var beginDateLabel: TextView
    private lateinit var endDateLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        beginDateLabel = findViewById<TextView>(R.id.from_date)
        endDateLabel = findViewById<TextView>(R.id.to_date)

        beginDateLabel.setOnClickListener { showDatePickerDialog(MainViewModel.DateType.BEGIN) }
        endDateLabel.setOnClickListener { showDatePickerDialog(MainViewModel.DateType.END) }

        val airportSpinner = findViewById<Spinner>(R.id.airport_spinner)

        viewModel.getAirportNamesListLiveData().observe(this) {
            val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item, it
            )

            airportSpinner.adapter = adapter
        }


        viewModel.getBeginDateLiveData().observe(this) {
            beginDateLabel.text = Utils.dateToString(it.time)
        }

        viewModel.getEndDateLiveData().observe(this) {
            endDateLabel.text = Utils.dateToString(it.time)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val begin = viewModel.getBeginDateLiveData().value!!.timeInMillis / 1000

            val end = viewModel.getEndDateLiveData().value!!.timeInMillis / 1000

            val selectedAirportIndex = airportSpinner.selectedItemPosition
            val airport = viewModel.getAirportListLiveData().value!![selectedAirportIndex]
            val icao = airport.icao
            val isArrival = findViewById<Switch>(R.id.airport_switch).isChecked

            val intent = Intent(this, FlightListActivity::class.java)

            intent.putExtra("BEGIN",begin)
            intent.putExtra("END",end)
            intent.putExtra("IS_ARRIVAL",isArrival)
            intent.putExtra("ICAO",icao)

            startActivity(intent)
        }

    }

    // open date picker dialog
    private fun showDatePickerDialog(dateType: MainViewModel.DateType) {
        // Date Select Listener.
        val dateSetListener =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                viewModel.updateCalendarLiveData(dateType, calendar)
            }

        val currentCalendar = if (dateType == MainViewModel.DateType.BEGIN) viewModel.getBeginDateLiveData().value else viewModel.getEndDateLiveData().value

        val datePickerDialog = DatePickerDialog(
            this,
            dateSetListener,
            currentCalendar!!.get(Calendar.YEAR),
            currentCalendar.get(Calendar.MONTH),
            currentCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}