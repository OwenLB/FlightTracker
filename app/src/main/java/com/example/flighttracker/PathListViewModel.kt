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

class PathListViewModel : ViewModel() {

    val pathListLiveData = MutableLiveData<List<String>>(ArrayList())

    fun getPathListLiveData(): LiveData<List<String>> {
        return pathListLiveData
    }

    fun setPathListLiveData(paths: List<String>) {
        pathListLiveData.value = paths
    }

    fun doRequest(icao24: String, time: Long) {

        viewModelScope.launch {
            val url = " https://opensky-network.org/api/tracks/all"
            val param = HashMap<String, String>()
            param.put("icao24", icao24)
            param.put("time", time.toString())

            val result = withContext(Dispatchers.IO) {
                RequestManager.getSuspended(url, param)
            }

            if(result != null) {
                Log.i("REQUEST", "result: $result")

                val pathList = ArrayList<String>()
                val parser = JsonParser()
                val jsonElement = parser.parse(result)

                for (pathObject in jsonElement.asJsonArray) {
                    pathList.add(Gson().fromJson(pathObject.asJsonObject, String::class.java))
                }

                setPathListLiveData(pathList)
            } else {
                Log.i("REQUEST", "result: null")
            }
        }
    }
}