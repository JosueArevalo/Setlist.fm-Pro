package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheVenueEntity(val id: String?,
                            val name: String?,
                            val city: CacheCityEntity?,
                            val url: String?)
