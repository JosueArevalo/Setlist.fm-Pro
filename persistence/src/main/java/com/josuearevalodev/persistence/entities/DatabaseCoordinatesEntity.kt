package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class DatabaseCoordinatesEntity(val lat: Float?,
                                     val long: Float?)
