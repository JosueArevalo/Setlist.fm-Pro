package com.josuearevalodev.domain.setlistfm.entities

data class SearchArtistsResponseEntity(
    val type: String,
    val itemsPerPage: Int,
    val page: Int,
    val total: Int,
    val artist: List<ArtistEntity>
)
