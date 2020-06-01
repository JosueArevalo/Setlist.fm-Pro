package com.josuearevalodev.setlistfmpro.di

import android.app.Application
import com.josuearevalodev.data.di.dataModule
import com.josuearevalodev.location.di.locationModule
import com.josuearevalodev.persistence.di.persistenceModule
import com.josuearevalodev.remote.di.remoteModule
import com.josuearevalodev.remote.httpclient.HttpClient
import com.josuearevalodev.remote.httpclient.HttpHeader
import com.josuearevalodev.setlistfmpro.BuildConfig
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.usecases.di.userCaseModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinManager(private val app: Application) {

    private val httpClient: HttpClient by app.inject()

    fun init() {
        val baseUrl = "https://api.setlist.fm/rest/1.0/"

        startKoin {
            androidContext(app)
            modules(appModule,
                userCaseModule,
                dataModule,
                remoteModule(baseUrl),
                persistenceModule,
                locationModule
            )
        }

        configureHttpClient()
    }

    private fun configureHttpClient() {
        httpClient.isDebugMode = BuildConfig.DEBUG
        httpClient.setHeader(HttpHeader("Accept", "application/json"))
        httpClient.setHeader(HttpHeader("x-api-key", app.getString(R.string.setlistfm_api_key)))
    }
}
