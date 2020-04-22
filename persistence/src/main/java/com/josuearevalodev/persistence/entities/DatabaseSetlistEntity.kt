package com.josuearevalodev.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setlists")
data class DatabaseSetlistEntity(
    @PrimaryKey val id: String,
    val versionId: String?,
    val eventDate: String?,
    val lastUpdated: String?,
    //TODO!!val artist: DatabaseArtistEntity?,
    val artistId: String,
    val venue: DatabaseVenueEntity?,
    //val venueJSON: String?,
    //TODO!!!val sets: DatabaseSetsEntity?,
    val url: String?
)
