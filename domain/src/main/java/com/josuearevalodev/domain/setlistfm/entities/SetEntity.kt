package com.josuearevalodev.domain.setlistfm.entities

data class SetEntity(
    val name: String = "",
    val encore: Int = -1,
    val song: List<SongEntity> = listOf()
)

