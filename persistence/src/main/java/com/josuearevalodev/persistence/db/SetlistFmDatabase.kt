package com.josuearevalodev.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josuearevalodev.domain.entities.DatabaseArtistEntity
import com.josuearevalodev.domain.entities.DatabaseSetlistEntity

@Database(entities = arrayOf(DatabaseArtistEntity::class, DatabaseSetlistEntity::class), version = 1)
@TypeConverters(SetlistFmTypeConverter::class)
abstract class SetlistFmDatabase : RoomDatabase() {
    abstract fun setlistFmDao() : SetlistFmDao
}
