package com.josuearevalodev.persistence.setlistfm.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class DatabaseArtistEntity(
    @PrimaryKey val mbid: String,
    val name: String?,
    val sortName: String?,
    val disambiguation: String?,
    val url: String?,
    val itemsPerPage: Int,
    val totalSetlists: Int,
    val lastPage1RemoteCall: Long

)

