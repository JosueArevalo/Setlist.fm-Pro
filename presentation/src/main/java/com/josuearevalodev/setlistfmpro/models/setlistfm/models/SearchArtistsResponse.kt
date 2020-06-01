package com.josuearevalodev.setlistfmpro.models.setlistfm.models

data class SearchArtistsResponse(
    val type: String = "",
    val itemsPerPage: Int = 0,
    val page: Int = 0,
    val total: Int = 0,
    val artist: List<Artist> = listOf()
)
