package com.josuearevalodev.persistence.entities

import androidx.room.Entity

@Entity
data class DatabaseCoordinatesEntity(val lat: Float?,
                                     val long: Float?)
