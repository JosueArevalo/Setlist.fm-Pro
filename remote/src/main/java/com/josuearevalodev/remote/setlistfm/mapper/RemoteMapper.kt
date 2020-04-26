package com.josuearevalodev.remote.setlistfm.mapper

import com.josuearevalodev.data.setlistfm.error.*
import com.josuearevalodev.domain.setlistfm.entities.*
import retrofit2.HttpException

//region entities
val RemoteSearchArtistsResponse.mapToSearchArtistsResponse: SearchArtistsResponse
    get() {
        return SearchArtistsResponse(
            type = this.type ?: "",
            itemsPerPage = this.itemsPerPage ?: 0,
            page = this.page ?: 0,
            total = this.total ?: 0,
            artist = this.artist?.map { it.mapToArtistEntity } ?: listOf()
            )
    }

val RemoteArtistEntity.mapToArtistEntity: ArtistEntity
    get() {
        return ArtistEntity(
            mbid = this.mbid ?: "",
            name = this.name ?: "",
            sortName = this.sortName ?: "",
            disambiguation = this.disambiguation ?: "",
            url = this.url ?: ""
        )
    }

val RemoteArtistSetlistsResponse.mapToArtistSetlistsResponse: ArtistSetlistsResponse
    get() {
        return ArtistSetlistsResponse(
            type = this.type ?: "",
            itemsPerPage = this.itemsPerPage ?: 0,
            page = this.page ?: 0,
            total = this.total ?: 0,
            setlist = this.setlist?.map { it.mapToSetlistEntity } ?: listOf()
        )
    }

val RemoteSetlistEntity.mapToSetlistEntity: SetlistEntity
    get() {
        return SetlistEntity(
            id = this.id ?: "",
            versionId = this.versionId ?: "",
            eventDate = this.eventDate ?: "",
            lastUpdated = this.lastUpdated ?: "",
            artist = this.artist?.mapToArtistEntity ?: ArtistEntity(),
            venue = this.venue?.mapToVenueEntity ?: VenueEntity(),
            sets = this.sets?.mapToSetsEntity ?: SetsEntity(),
            url = this.url ?: ""
        )
    }

val RemoteVenueEntity.mapToVenueEntity: VenueEntity
    get() {
        return VenueEntity(
            id = this.id ?: "",
            name = this.name ?: "",
            city = this.city?.mapToCityEntity ?: CityEntity(),
            url = this.url ?: ""
        )
    }

val RemoteSetsEntity.mapToSetsEntity: SetsEntity
    get() {
        return SetsEntity(
            set = this.set.map { it.mapToSetEntity }
        )
    }

val RemoteSetEntity.mapToSetEntity: SetEntity
    get() {
        return SetEntity(
            song = this.song?.map { it.mapToSongEntity } ?: listOf()
        )
    }

val RemoteSongEntity.mapToSongEntity: SongEntity
    get() {
        return SongEntity(
            name = this.name ?: ""
        )
    }

val RemoteCityEntity.mapToCityEntity: CityEntity
    get() {
        return CityEntity(
            id = this.id ?: "",
            name = this.name ?: "",
            state = this.state ?: "",
            stateCode = this.stateCode ?: "",
            coords = this.coords?.mapToCoordinatesEntity ?: CoordinatesEntity(),
            country = this.country?.mapToCountryEntity ?: CountryEntity()
        )
    }

val RemoteCoordinatesEntity.mapToCoordinatesEntity: CoordinatesEntity
    get() {
        return CoordinatesEntity(
            lat = this.lat ?: 0f,
            long = this.long ?: 0f
        )
    }

val RemoteCountryEntity.mapToCountryEntity: CountryEntity
    get() {
        return CountryEntity(
            code = this.code ?: "",
            name = this.name ?: ""
        )
    }
//endregion

//region error
val HttpException.mapToRemoteError: RemoteError
    get() {
        return when (code()) {
            400 -> BadRequest(this)
            401 -> Unauthorized(this)
            404 -> NotFound(this)
            429 -> TooManyRequests(this)
            else -> Unexpected(this)
        }
    }
//endregion
