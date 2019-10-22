package com.albertmiro.troopertrips

import android.app.Application
import com.albertmiro.troopertrips.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TripsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            androidContext(this@TripsApp)
            Modules.init()
        }

    }
}