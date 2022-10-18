package com.example.flighttracker

/**
 * Created by sergio on 07/11/2021
 * All rights reserved GoodBarber
 */
data class Airport(
    val code: String,
    val lat: String,
    val lon: String,
    val name: String,
    val city: String,
    val state: String,
    val country: String,
    val woeid: String,
    val tz: String,
    val phone: String,
    val type: String,
    val email: String,
    val url: String,
    val runway_length: String,
    val elev: String,
    val icao: String,
    val direct_flights: String,
    val carriers: String
) {
    fun getFormattedName(): String {
        return "$code $city ($country)"
    }
}