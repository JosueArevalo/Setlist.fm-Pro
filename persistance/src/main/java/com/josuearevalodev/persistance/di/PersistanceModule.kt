package com.josuearevalodev.persistance.di

import android.content.Context
import androidx.room.Room
import com.josuearevalodev.persistance.db.SetlistFmDao
import com.josuearevalodev.persistance.db.SetlistFmDatabase
import org.koin.dsl.bind
import org.koin.dsl.module

val persistanceModule = module {

    single { Room.databaseBuilder(
        get(),
        SetlistFmDatabase::class.java, "setlistfm-db"
        ).build()
    } bind SetlistFmDao::class /* TODO: Check */
}
