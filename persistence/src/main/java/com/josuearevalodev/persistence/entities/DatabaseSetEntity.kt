package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class DatabaseSetEntity(
    val song: List<DatabaseSongEntity>?
)

