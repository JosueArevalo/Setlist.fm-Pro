package com.josuearevalodev.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josuearevalodev.persistence.entities.DatabaseArtistEntity

@Entity(tableName = "setlists")
data class DatabaseSetlistEntity(
    @PrimaryKey val id: String,
    val versionId: String?,
    val eventDate: String?,
    val lastUpdated: String?,
    val artist: DatabaseArtistEntity?,
    val artistId: String,
    val venue: DatabaseVenueEntity?,
    val sets: DatabaseSetsEntity?,
    val url: String?
)
