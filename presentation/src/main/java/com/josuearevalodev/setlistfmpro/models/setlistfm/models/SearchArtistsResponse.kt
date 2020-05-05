package com.josuearevalodev.setlistfmpro.models.setlistfm.models

data class SearchArtistsResponse(
    val type: String,
    val itemsPerPage: Int,
    val page: Int,
    val total: Int,
    val artist: List<Artist>
)
