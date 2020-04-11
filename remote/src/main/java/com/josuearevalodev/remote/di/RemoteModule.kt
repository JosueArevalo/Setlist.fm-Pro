package com.josuearevalodev.remote.di

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.remote.datasource.RemoteSetlistFmDataSource
import com.josuearevalodev.remote.datasource.RemoteSetlistFmDataSourceImpl
import com.josuearevalodev.remote.httpclient.HttpClient
import com.josuearevalodev.remote.httpclient.HttpClientImpl
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

fun remoteModule(baseUrl: String) = module {

    single<RemoteSetlistFmDataSource>(named("remote")) {
        RemoteSetlistFmDataSourceImpl(get(), baseUrl)
    } bind SetListFmDataSource::class


    single { HttpClientImpl() } bind HttpClient::class
}
