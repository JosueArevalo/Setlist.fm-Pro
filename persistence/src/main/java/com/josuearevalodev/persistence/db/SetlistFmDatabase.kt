package com.josuearevalodev.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josuearevalodev.domain.entities.CacheArtistEntity

@Database(entities = arrayOf(CacheArtistEntity::class), version = 1)
abstract class SetlistFmDatabase : RoomDatabase() {
    abstract fun setlistFmDao() : SetlistFmDao
}
