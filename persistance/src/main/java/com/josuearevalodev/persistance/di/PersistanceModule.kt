package com.josuearevalodev.persistance.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.josuearevalodev.persistance.db.SetlistFmDatabase
import org.koin.dsl.bind
import org.koin.dsl.module

val persistanceModule = module {

    single { Room.databaseBuilder(
        get(),
        SetlistFmDatabase::class.java, "setlistfm-db"
        ).build()
    } bind RoomDatabase::class /* TODO: Check */
}
