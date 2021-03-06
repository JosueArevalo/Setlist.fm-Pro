package com.josuearevalodev.setlistfmpro.models.setlistfm

import com.josuearevalodev.domain.setlistfm.entities.*
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Set
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.*

val ArtistSetlistsResponseEntity.mapToArtistSetlistsResponse: ArtistSetlistsResponse
    get() = ArtistSetlistsResponse(
        type = type,
        itemsPerPage = itemsPerPage,
        page = page,
        total = total,
        setlist = setlist.map { it.mapToSetlist }
    )

val SetlistEntity.mapToSetlist: Setlist
    get() = Setlist(
        id = id,
        versionId = versionId,
        eventDate = eventDate,
        lastUpdated = lastUpdated,
        artist = artist.mapToArtist,
        venue = venue.mapToVenue,
        tour = tour.mapToTour,
        sets = sets.mapToSets,
        url = url
    )

val TourEntity.mapToTour: Tour
    get() = Tour(
        name = name
    )

val VenueEntity.mapToVenue: Venue
    get() = Venue(
        id = id,
        name = name,
        city = city.mapToCity,
        url = url
    )

val CityEntity.mapToCity: City
    get() = City(
        id = id,
        name = name,
        state = state,
        stateCode = stateCode,
        coords = coords.mapToCoordinates,
        country = country.mapToCountry
    )

val CoordinatesEntity.mapToCoordinates: Coordinates
    get() = Coordinates(
        lat = lat,
        long = long
    )

val CountryEntity.mapToCountry: Country
    get() = Country(
        code = code,
        name = name
    )

val SetsEntity.mapToSets: Sets
    get() = Sets(
        set = set.map { it.mapToSet }
    )

val SetEntity.mapToSet: Set
    get() = Set(
        name = name,
        encore = encore,
        song = song.map { it.mapToSong }
    )

val SongEntity.mapToSong: Song
    get() = Song(
        name = name,
        info = info,
        tape = tape
    )

val ArtistEntity.mapToArtist: Artist
    get() = Artist(
        mbid = mbid,
        name = name,
        sortName = sortName,
        disambiguation = disambiguation,
        url = url,
        itemsPerPage = itemsPerPage,
        totalSetlists = totalSetlists
    )
