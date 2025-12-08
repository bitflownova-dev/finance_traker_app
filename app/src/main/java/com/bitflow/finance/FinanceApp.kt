package com.bitflow.finance

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FinanceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        System.setProperty("log4j2.disable.jmx", "true")
        System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.SystemOutLogger")
    }
}
