package com.josuearevalodev.persistence.setlistfm.entities

import androidx.room.Entity

@Entity
data class DatabaseSetsEntity(
    val set: List<DatabaseSetEntity>?
)
