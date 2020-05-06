package com.josuearevalodev.persistence.setlistfm.entities

import androidx.room.Entity

@Entity
data class DatabaseSongEntity(
    val name: String?,
    val info: String?,
    val tape: Boolean?
)
