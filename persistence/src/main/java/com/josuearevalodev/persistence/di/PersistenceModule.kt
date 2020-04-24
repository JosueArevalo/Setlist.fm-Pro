package com.josuearevalodev.persistence.di

import androidx.room.Room
import com.josuearevalodev.data.setlistfm.datasource.SetListFmDatabaseDataSource
import com.josuearevalodev.persistence.datasource.DatabaseSetlistFmDataSourceImpl
import com.josuearevalodev.persistence.db.SetlistFmDao
import com.josuearevalodev.persistence.db.SetlistFmDatabase
import org.koin.dsl.bind
import org.koin.dsl.module

val persistenceModule = module {

    single {
        Room.databaseBuilder(
            get(),
            SetlistFmDatabase::class.java,
            "setlistfm-db"
        ).build()
    } //bind SetlistFmDatabase::class /* TODO: Check */

    single {
        get<SetlistFmDatabase>().setlistFmDao()
    } bind SetlistFmDao::class

    single {
        DatabaseSetlistFmDataSourceImpl(get())
    } bind SetListFmDatabaseDataSource::class
}
