package com.josuearevalodev.domain.setlistfm.entities

data class SearchArtistsResponseEntity(
    val type: String = "",
    val itemsPerPage: Int = 0,
    val page: Int = 0,
    val total: Int = 0,
    val artist: List<ArtistEntity> = listOf()
)
