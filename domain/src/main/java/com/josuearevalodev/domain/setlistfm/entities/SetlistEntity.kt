package com.josuearevalodev.domain.setlistfm.entities

data class SetlistEntity(val id: String,
                         val versionId: String,
                         val eventDate: String,
                         val lastUpdated: String,
                         val artist: ArtistEntity,
                         val venue: VenueEntity,
                         val sets: SetsEntity,
                         val url: String)
