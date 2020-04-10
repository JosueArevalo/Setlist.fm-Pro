package com.josuearevalodev.data.di

import com.josuearevalodev.data.repositories.SetListFmRepositoryImpl
import com.josuearevalodev.domain.repository.SetListFmRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    factory { SetListFmRepositoryImpl() } bind SetListFmRepository::class
}
