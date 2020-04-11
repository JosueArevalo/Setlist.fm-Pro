package com.josuearevalodev.data.di

import com.josuearevalodev.data.setlistfm.repository.SetListFmRepositoryImpl
import com.josuearevalodev.domain.repository.SetListFmRepository
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    single { SetListFmRepositoryImpl(get(named("remote"))) } bind SetListFmRepository::class
}
