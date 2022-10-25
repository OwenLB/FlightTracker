package com.example.flighttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xml.sax.Parser

class FlightListViewModel : ViewModel() {

    val flightListLiveData = MutableLiveData<List<FlightModel>>(ArrayList())

    fun getFlightListLiveData(): LiveData<List<FlightModel>> {
        return flightListLiveData
    }

    fun setFlightListLiveData(flights: List<FlightModel>) {
        flightListLiveData.value = flights
    }

    fun doRequest(begin: Long, end: Long, isArrival: Boolean, icao: String) {

        viewModelScope.launch {
            val url = if (isArrival) {
                "https://opensky-network.org/api/flights/arrival"
            } else {
                "https://opensky-network.org/api/flights/departure"
            }
            val param = HashMap<String, String>()
            param.put("begin", begin.toString())
            param.put("end", end.toString())
            param.put("airport", icao)

            val result = withContext(Dispatchers.IO) {
                RequestManager.getSuspended(url, param)
            }

            if(result != null) {
                Log.i("REQUEST", "result: $result")

                val flightList = ArrayList<FlightModel>()
                val parser = JsonParser()
                val jsonElement = parser.parse(result)

                for (flightObject in jsonElement.asJsonArray) {
                    flightList.add(Gson().fromJson(flightObject.asJsonObject, FlightModel::class.java))
                }

                setFlightListLiveData(flightList)
            } else {
                Log.i("REQUEST", "result: null")
            }
        }
    }
}