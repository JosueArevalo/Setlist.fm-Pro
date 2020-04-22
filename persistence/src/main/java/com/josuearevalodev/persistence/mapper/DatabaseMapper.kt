package com.josuearevalodev.persistence.mapper

import androidx.room.EmptyResultSetException
import com.josuearevalodev.domain.entities.*
import com.josuearevalodev.persistence.error.DatabaseError

//region entities
val DatabaseArtistEntity.mapToArtistEntity: ArtistEntity
    get() {
        return ArtistEntity(
            mbid = this.mbid,
            name = this.name ?: "",
            sortName = this.sortName ?: "",
            disambiguation = this.disambiguation ?: "",
            url = this.url ?: ""
        )
    }

val ArtistEntity.mapToDatabaseArtistEntity: DatabaseArtistEntity
    get() {
        return DatabaseArtistEntity(
            mbid = mbid,
            name = name,
            sortName = sortName,
            disambiguation = disambiguation,
            url = url
        )
    }

// TODOOOOOOO
val DatabaseSetlistEntity.mapToSetlistEntity: SetlistEntity
    get() = SetlistEntity(
        id = id, versionId = versionId ?: "", eventDate = eventDate ?: "", lastUpdated = lastUpdated ?: "", artist = ArtistEntity(
            mbid = this.id,
            name = "",
            sortName = "",
            disambiguation = "",
            url = ""
        ), venue = VenueEntity(
            id = "", name = "", city = CityEntity(
                id = "",
                name = "",
                state = "",
                stateCode = "",
                coords = CoordinatesEntity(lat = 0.0f, long = 0.0f),
                country = CountryEntity(code = "", name = "")
            ), url = ""
        ), sets = SetsEntity(set = listOf()), url = ""
    )

// TODOOOOOOOO
val SetlistEntity.mapToDatabaseSetlistEntity: DatabaseSetlistEntity
    get() = DatabaseSetlistEntity(
        id = id,
        versionId = versionId,
        eventDate = eventDate,
        lastUpdated = lastUpdated,
        artistId = artist.mbid,
        /*TODOvenue = null,
        sets = null,*/
        url = url
    )


//endregion

//region error
val Throwable.mapToDatabaseError : DatabaseError
    get() {
        return when (this) {
            is EmptyResultSetException -> DatabaseError.NoResultsFound(this)
            else -> DatabaseError.Unexpected(this)
        }
    }
//endregion
