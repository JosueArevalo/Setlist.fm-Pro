package com.josuearevalodev.persistence.setlistfm.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.josuearevalodev.persistence.setlistfm.entities.DatabaseArtistEntity
import com.josuearevalodev.persistence.setlistfm.entities.DatabaseSetsEntity
import com.josuearevalodev.persistence.setlistfm.entities.DatabaseTourEntity
import com.josuearevalodev.persistence.setlistfm.entities.DatabaseVenueEntity

class SetlistFmTypeConverter {

    //region DatabaseVenueEntity
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

    //endregion

    //region DatabaseTourEntity

    @TypeConverter
    fun databaseTourEntityToString(input: DatabaseTourEntity?): String? {
        return input?.let {
            Gson().toJson(input)
        }
    }

    @TypeConverter
    fun stringToDatabaseTourEntity(input: String?): DatabaseTourEntity? {
        return input?.let {
            Gson().fromJson(input, DatabaseTourEntity::class.java)
        }
    }

    //endregion

    //region DatabaseSetsEntity
    @TypeConverter
    fun databaseSetsEntityToString(input: DatabaseSetsEntity?): String? {
        return input?.let {
            Gson().toJson(input)
        }
    }

    @TypeConverter
    fun stringToDatabaseSetsEntity(input: String?): DatabaseSetsEntity? {
        return input?.let {
            Gson().fromJson(input, DatabaseSetsEntity::class.java)
        }
    }
    //endregion

    //region DatabaseArtistEntity
    @TypeConverter
    fun databaseArtistEntityToString(input: DatabaseArtistEntity?): String? {
        return input?.let {
            Gson().toJson(input)
        }
    }

    @TypeConverter
    fun stringToDatabaseArtistEntity(input: String?): DatabaseArtistEntity? {
        return input?.let {
            Gson().fromJson(input, DatabaseArtistEntity::class.java)
        }
    }
    //endregion

}
