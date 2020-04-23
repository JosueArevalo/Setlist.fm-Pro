package com.josuearevalodev.persistence.entities

import androidx.room.Entity

@Entity
data class DatabaseSetsEntity(
    val set: List<DatabaseSetEntity>?
)
