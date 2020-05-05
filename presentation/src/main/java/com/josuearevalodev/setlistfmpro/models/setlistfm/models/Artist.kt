package com.josuearevalodev.domain.setlistfm.entities

data class Artist(
    val mbid: String = "",
    val name: String = "",
    val sortName: String = "",
    val disambiguation: String = "",
    val url: String = "",
    val itemsPerPage: Int = 0,
    val totalSetlists: Int = 0
)

