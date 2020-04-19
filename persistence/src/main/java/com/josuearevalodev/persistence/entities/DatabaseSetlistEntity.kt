package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class DatabaseSetlistEntity(val id: String?,
                                 val versionId: String?,
                                 val eventDate: String?,
                                 val lastUpdated: String?,
                                 val artist: DatabaseArtistEntity?,
                                 val venue: DatabaseVenueEntity?,
                                 val sets: DatabaseSetsEntity?,
                                 val url: String?)
