package com.josuearevalodev.domain.setlistfm.entities

import com.josuearevalodev.remote.setlistfm.entities.RemoteTourEntity

data class RemoteSetlistEntity(val id: String?,
                               val versionId: String?,
                               val eventDate: String?,
                               val lastUpdated: String?,
                               val artist: RemoteArtistEntity?,
                               val venue: RemoteVenueEntity?,
                               val tour: RemoteTourEntity?,
                               val sets: RemoteSetsEntity?,
                               val url: String?)
