package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class DatabaseVenueEntity(val id: String?,
                               val name: String?,
                               val city: DatabaseCityEntity?,
                               val url: String?)
