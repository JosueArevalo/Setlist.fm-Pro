package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheCityEntity(val id: String?,
                           val name: String?,
                           val state: String?,
                           val stateCode: String?,
                           val coords: CacheCoordinatesEntity?,
                           val country: CacheCountryEntity?)
