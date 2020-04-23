package com.josuearevalodev.persistence.entities

import com.josuearevalodev.persistence.entities.DatabaseArtistEntity

data class DatabaseSearchArtistsResponse(
    val type: String?,
    val itemsPerPage: Int?,
    val page: Int?,
    val total: Int?,
    val artist: List<DatabaseArtistEntity>?
)
