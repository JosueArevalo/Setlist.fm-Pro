package com.josuearevalodev.setlistfmpro.framework

import android.app.Application
import com.josuearevalodev.setlistfmpro.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SetlistfmProApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SetlistfmProApplication)
            modules(appModule)
        }
    }
}