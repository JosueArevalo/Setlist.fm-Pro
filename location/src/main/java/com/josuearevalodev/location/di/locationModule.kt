package com.josuearevalodev.location.di

import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.location.location.datasource.LocationDataSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.solodovnikov.rx2locationmanager.RxLocationManager

val locationModule = module {

    single {
        LocationDataSourceImpl(get())
    } bind LocationDataSource::class

    single {
        RxLocationManager(get())
    }
}
