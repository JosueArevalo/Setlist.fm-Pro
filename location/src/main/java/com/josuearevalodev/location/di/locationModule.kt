package com.josuearevalodev.location.di

import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.location.location.datasource.LocationDataSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {

    single {
        LocationDataSourceImpl()
    } bind LocationDataSource::class
}
