package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheSetlistEntity(val id: String?,
                              val versionId: String?,
                              val eventDate: String?,
                              val lastUpdated: String?,
                              val artist: CacheArtistEntity?,
                              val venue: CacheVenueEntity?,
                              val sets: CacheSetsEntity?,
                              val url: String?)
