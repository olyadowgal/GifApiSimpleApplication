package com.example.gifapisimpleapplication.application

import android.app.Application
import com.example.gifapisimpleapplication.AppComponent
import com.facebook.stetho.Stetho
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponent.init(this)
        Stetho.initializeWithDefaults(this)
    }

}