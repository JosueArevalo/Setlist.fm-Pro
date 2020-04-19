package com.josuearevalodev.persistance.di

import androidx.room.Room
import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.persistance.datasource.CacheSetlistFmDataSourceImpl
import com.josuearevalodev.persistance.db.SetlistFmDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val persistanceModule = module {

    single { Room.databaseBuilder(
        get(),
        SetlistFmDatabase::class.java, "setlistfm-db"
        ).build()
    } bind SetlistFmDatabase::class /* TODO: Check */

    single<SetListFmDataSource>(named("cached")) {
        CacheSetlistFmDataSourceImpl(get())
    } bind SetListFmDataSource::class
}
