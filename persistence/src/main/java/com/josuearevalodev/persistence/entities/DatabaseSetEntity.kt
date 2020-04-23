package com.josuearevalodev.persistence.entities

import androidx.room.Entity

@Entity
data class DatabaseSetEntity(
    val song: List<DatabaseSongEntity>?
)

