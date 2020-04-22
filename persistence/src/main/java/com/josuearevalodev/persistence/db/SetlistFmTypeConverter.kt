package com.josuearevalodev.persistence.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.josuearevalodev.domain.entities.DatabaseVenueEntity

class SetlistFmTypeConverter {

    @TypeConverter
    fun databaseVenueEntityToString(input: DatabaseVenueEntity?): String? {
        return input?.let {
            Gson().toJson(input)
        }
    }

    @TypeConverter
    fun stringToDatabaseVenueEntity(input: String?): DatabaseVenueEntity? {
        return input?.let {
            Gson().fromJson(input, DatabaseVenueEntity::class.java)
        }
    }
}
