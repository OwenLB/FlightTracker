package com.example.flighttracker

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources

/**
 * Created by sergio on 2019-11-10
 * All rights reserved GoodBarber
 */
class FlightApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        application = this
        appContext = getApplicationContext()

    }

    companion object {
        private val TAG = FlightApplication::class.java.simpleName

        var appContext: Context? = null
            private set

        var application: Application? = null
            private set

        val appResources: Resources
            get() = appContext!!.resources

        val appAssetManager: AssetManager
            get() = appContext!!.assets
    }
}