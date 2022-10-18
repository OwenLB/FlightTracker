package com.example.flighttracker

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by sergio on 04/03/2021
 * All rights reserved GoodBarber
 */
class RequestManager {

    interface RequestListener {
        fun onRequestSuccess(result: String?)
        fun onRequestFailed()
    }

    companion object {
        fun get(
            sourceUrl: String?,
            params: Map<String, String>?
        ): String? {
            val result = StringBuilder()
            var finalSourceUrl = sourceUrl
            try {
                //Params
                var c = 0
                for (key in params!!.keys) {
                    val value = params[key]
                    if (c != 0) {
                        finalSourceUrl += "&"
                    } else {
                        finalSourceUrl += "?"
                    }

                    finalSourceUrl += "$key=$value"
                    c++

                }
                val url = URL(finalSourceUrl)
                val httpURLConnection =
                    url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.connectTimeout = 10000
                httpURLConnection.readTimeout = 10000
                Log.i(
                    "RequestManager",
                    "Request[GET]: \nURL: $finalSourceUrl\nNb Param: $c"
                )
                val reader =
                    BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result.append(line)
                }
                reader.close()
                return result.toString()
            } catch (e: IOException) {
                Log.e(
                    "RequestManager",
                    "Error while doing GET request (url: " + finalSourceUrl + ") - " + e.message
                )
            }
            return null
        }

        suspend fun getSuspended(
            sourceUrl: String?,
            params: Map<String, String>?
        ): String? {
            val result = StringBuilder()
            var finalSourceUrl = sourceUrl
            try {
                //Params
                var c = 0
                for (key in params!!.keys) {
                    val value = params[key]
                    if (c != 0) {
                        finalSourceUrl += "&"
                    } else {
                        finalSourceUrl += "?"
                    }

                    finalSourceUrl += "$key=$value"
                    c++

                }
                val url = URL(finalSourceUrl)
                val httpURLConnection =
                    url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.connectTimeout = 30000
                httpURLConnection.readTimeout = 30000
                Log.i(
                    "RequestManager",
                    "Request[GET]: \nURL: $finalSourceUrl\nNb Param: $c"
                )
                val reader =
                    BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result.append(line)
                }
                reader.close()
                return result.toString()
            } catch (e: IOException) {
                Log.e(
                    "RequestManager",
                    "Error while doing GET request (url: " + finalSourceUrl + ") - " + e.message
                )
            }
            return null
        }
    }

}