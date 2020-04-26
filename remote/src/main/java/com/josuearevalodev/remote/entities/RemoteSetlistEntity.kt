package com.josuearevalodev.domain.setlistfm.entities

data class RemoteSetlistEntity(val id: String?,
                               val versionId: String?,
                               val eventDate: String?,
                               val lastUpdated: String?,
                               val artist: RemoteArtistEntity?,
                               val venue: RemoteVenueEntity?,
                               val sets: RemoteSetsEntity?,
                               val url: String?)
