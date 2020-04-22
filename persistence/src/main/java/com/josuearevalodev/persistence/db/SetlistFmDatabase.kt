package com.josuearevalodev.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josuearevalodev.domain.entities.DatabaseArtistEntity
import com.josuearevalodev.domain.entities.DatabaseSetlistEntity

@Database(entities = arrayOf(DatabaseArtistEntity::class, DatabaseSetlistEntity::class), version = 1)
abstract class SetlistFmDatabase : RoomDatabase() {
    abstract fun setlistFmDao() : SetlistFmDao
}
