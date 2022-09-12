package com.leviancode.android.infotextest

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BestWeatherApp : Application(){

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}