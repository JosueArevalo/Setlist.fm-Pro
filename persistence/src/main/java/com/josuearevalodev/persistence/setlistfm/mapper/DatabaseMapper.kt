package com.josuearevalodev.persistence.setlistfm.mapper

import androidx.room.EmptyResultSetException
import com.josuearevalodev.domain.setlistfm.entities.*
import com.josuearevalodev.persistence.setlistfm.entities.*
import com.josuearevalodev.persistence.setlistfm.error.DatabaseError

//region entities - Database (Data) --> Domain: E.g. Convert to return data from Database
val DatabaseArtistEntity.mapToArtistEntity: ArtistEntity
    get() {
        return ArtistEntity(
            mbid = this.mbid,
            name = this.name ?: "",
            sortName = this.sortName ?: "",
            disambiguation = this.disambiguation ?: "",
            url = this.url ?: "",
            itemsPerPage = this.itemsPerPage ?: 0,
            totalSetlists = this.totalSetlists ?: 0
        )
    }

val DatabaseSetlistEntity.mapToSetlistEntity: SetlistEntity
    get() = SetlistEntity(
        id = id,
        versionId = versionId ?: "",
        eventDate = eventDate ?: "",
        lastUpdated = lastUpdated ?: "",
        artist = artist?.mapToArtistEntity ?: ArtistEntity(),
        venue = venue?.mapToVenueEntity ?: VenueEntity(),
        sets = sets?.mapToSetsEntity ?: SetsEntity(),
        url = url ?: ""
    )

val DatabaseVenueEntity.mapToVenueEntity: VenueEntity
    get() = VenueEntity(
        id = id ?: "",
        name = name ?: "",
        city = city?.mapToCityEntity ?: CityEntity(),
        url = url ?: ""
    )

val DatabaseCityEntity.mapToCityEntity: CityEntity
    get() = CityEntity(
        id = id ?: "",
        name = name ?: "",
        state = state ?: "",
        stateCode = stateCode ?: "",
        coords = coords?.mapToCoordinatesEntity ?: CoordinatesEntity(),
        country = country?.mapToCountryEntity ?: CountryEntity()
    )

val DatabaseCountryEntity.mapToCountryEntity: CountryEntity
    get() = CountryEntity(
        code = code ?: "",
        name = name ?: ""
    )

val DatabaseSetsEntity.mapToSetsEntity: SetsEntity
    get() = SetsEntity(
        set = set?.map { it.mapToSetEntity } ?: listOf()
    )

val DatabaseCoordinatesEntity.mapToCoordinatesEntity: CoordinatesEntity
    get() = CoordinatesEntity(
        lat = lat ?: 0.0f,
        long = long ?: 0.0f
    )

val DatabaseSetEntity.mapToSetEntity: SetEntity
    get() = SetEntity(
        name = name ?: "",
        encore = encore ?: 0,
        song = song?.map { it.mapToSongEntity } ?: listOf()
    )

val DatabaseSongEntity.mapToSongEntity: SongEntity
    get() = SongEntity(
        name = name ?: ""
    )

//endregion

//region entities - Domain --> Database (Data): E.g. Convert to insert value to Database
val ArtistEntity.mapToDatabaseArtistEntity: DatabaseArtistEntity
    get() {
        return DatabaseArtistEntity(
            mbid = mbid,
            name = name,
            sortName = sortName,
            disambiguation = disambiguation,
            url = url,
            itemsPerPage = itemsPerPage,
            totalSetlists = totalSetlists
        )
    }



val SetlistEntity.mapToDatabaseSetlistEntity: DatabaseSetlistEntity
    get() = DatabaseSetlistEntity(
        id = id,
        versionId = versionId,
        eventDate = eventDate,
        lastUpdated = lastUpdated,
        artistId = artist.mbid,
        artist = artist.mapToDatabaseArtistEntity,
        venue = venue.mapToDatabaseVenueEntity,
        tour = tour.mapToDatabaseTourEntity,
        sets = sets.mapToDatabaseSetsEntity,
        url = url
    )

val TourEntity.mapToDatabaseTourEntity: DatabaseTourEntity
    get() = DatabaseTourEntity(
        name = name
    )

val VenueEntity.mapToDatabaseVenueEntity: DatabaseVenueEntity
    get() = DatabaseVenueEntity(
        id = id,
        name = name,
        city = city.mapToDatabaseCityEntity,
        url = url
    )

val CityEntity.mapToDatabaseCityEntity: DatabaseCityEntity
    get() = DatabaseCityEntity(
        id = id,
        name = name,
        state = state,
        stateCode = stateCode,
        coords = coords.mapToDatabaseCoordinatesEntity,
        country = country.mapToDatabaseCountryEntity
    )

val CoordinatesEntity.mapToDatabaseCoordinatesEntity: DatabaseCoordinatesEntity
    get() = DatabaseCoordinatesEntity(
        lat = lat,
        long = long
    )

val CountryEntity.mapToDatabaseCountryEntity: DatabaseCountryEntity
    get() = DatabaseCountryEntity(
        code = code,
        name = name
    )

val SetsEntity.mapToDatabaseSetsEntity: DatabaseSetsEntity
    get() = DatabaseSetsEntity(
        set = set.map { it.mapToDatabaseSetEntity }
    )

val SetEntity.mapToDatabaseSetEntity: DatabaseSetEntity
    get() = DatabaseSetEntity(
        name = name,
        encore = encore,
        song = song.map { it.mapToDatabaseSongEntity }
    )

val SongEntity.mapToDatabaseSongEntity: DatabaseSongEntity
    get() = DatabaseSongEntity(
        name = name,
        info = info,
        tape = tape
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
