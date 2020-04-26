package com.josuearevalodev.data.di

import com.josuearevalodev.data.setlistfm.repository.SetListFmRepositoryImpl
import com.josuearevalodev.domain.setlistfm.repository.SetListFmRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    single {
        SetListFmRepositoryImpl(
            get(),
            get()
        )
    } bind SetListFmRepository::class
}
