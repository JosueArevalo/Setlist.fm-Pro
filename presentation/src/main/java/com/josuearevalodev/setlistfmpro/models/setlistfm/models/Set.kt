package com.josuearevalodev.setlistfmpro.models.setlistfm.models

data class Set(
    val name: String = "",
    val encore: Int = 0,
    val song: List<Song> = listOf()
)

