package com.josuearevalodev.setlistfmpro.framework

import android.app.Application
import com.josuearevalodev.setlistfmpro.di.KoinManager

class SetlistfmProApplication : Application() {

    //private val httpClient: HttpClient by inject()
    private val koinManager: KoinManager by lazy { KoinManager(this) }

    override fun onCreate() {
        super.onCreate()

        koinManager.init()
    }
}
