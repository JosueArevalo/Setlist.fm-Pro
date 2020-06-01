package com.josuearevalodev.persistence.setlistfm.entities

import androidx.room.Entity

@Entity
data class DatabaseCoordinatesEntity(
    val lat: Float?,
    val long: Float?
)
