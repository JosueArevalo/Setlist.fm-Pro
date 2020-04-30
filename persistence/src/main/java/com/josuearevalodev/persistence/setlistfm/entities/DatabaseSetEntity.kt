package com.josuearevalodev.persistence.setlistfm.entities

import androidx.room.Entity

@Entity
data class DatabaseSetEntity(
    val song: List<DatabaseSongEntity>?
)

