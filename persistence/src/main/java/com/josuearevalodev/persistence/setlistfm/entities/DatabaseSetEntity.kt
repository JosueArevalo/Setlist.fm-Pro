package com.josuearevalodev.persistence.setlistfm.entities

import androidx.room.Entity

@Entity
data class DatabaseSetEntity(
    val name: String?,
    val encore: Int?,
    val song: List<DatabaseSongEntity>?
)

