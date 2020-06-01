package com.josuearevalodev.persistence.setlistfm.entities

data class DatabaseSearchArtistsResponseEntity(
    val type: String?,
    val itemsPerPage: Int?,
    val page: Int?,
    val total: Int?,
    val artist: List<DatabaseArtistEntity>?
)
