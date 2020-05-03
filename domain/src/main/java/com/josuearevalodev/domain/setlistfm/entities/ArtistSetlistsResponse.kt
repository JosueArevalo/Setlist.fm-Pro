package com.josuearevalodev.domain.setlistfm.entities

data class ArtistSetlistsResponse(
    val type: String = "",
    val itemsPerPage: Int = 0,
    val page: Int = 0,
    val total: Int = 0,
    val setlist: List<SetlistEntity> = listOf()
)
