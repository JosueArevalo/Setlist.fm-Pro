package com.josuearevalodev.remote.di

import com.josuearevalodev.data.setlistfm.datasource.SetListFmRemoteDataSource
import com.josuearevalodev.remote.httpclient.HttpClient
import com.josuearevalodev.remote.httpclient.HttpClientImpl
import com.josuearevalodev.remote.setlistfm.datasource.RemoteSetlistFmDataSourceImpl
import com.josuearevalodev.remote.utils.NetworkInfoProvider
import com.josuearevalodev.remote.utils.NetworkInfoProviderImpl
import org.koin.dsl.bind
import org.koin.dsl.module

fun remoteModule(baseUrl: String) = module {

    single {
        RemoteSetlistFmDataSourceImpl(get(), baseUrl, get())
    } bind SetListFmRemoteDataSource::class

    single { HttpClientImpl() } bind HttpClient::class

    single { NetworkInfoProviderImpl(get()) } bind NetworkInfoProvider::class
}
