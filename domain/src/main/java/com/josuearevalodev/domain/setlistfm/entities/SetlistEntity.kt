package com.josuearevalodev.domain.setlistfm.entities

data class SetlistEntity(
    val id: String = "",
    val versionId: String = "",
    val eventDate: String = "",
    val lastUpdated: String = "",
    val artist: ArtistEntity = ArtistEntity(),
    val venue: VenueEntity = VenueEntity(),
    val tour: TourEntity = TourEntity(),
    val sets: SetsEntity = SetsEntity(),
    val url: String = ""
)
