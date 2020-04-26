package com.josuearevalodev.persistence.setlistfm.entities

data class DatabaseSearchArtistsResponse(
    val type: String?,
    val itemsPerPage: Int?,
    val page: Int?,
    val total: Int?,
    val artist: List<DatabaseArtistEntity>?
)
