package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class DatabaseCityEntity(val id: String?,
                              val name: String?,
                              val state: String?,
                              val stateCode: String?,
                              val coords: DatabaseCoordinatesEntity?,
                              val country: DatabaseCountryEntity?)
