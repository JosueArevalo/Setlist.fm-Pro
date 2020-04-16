package com.josuearevalodev.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class CacheArtistEntity(
    @PrimaryKey val mbid: String,
    val name: String?,
    val sortName: String?,
    val disambiguation: String?,
    val url: String?
)

