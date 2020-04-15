package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheSetsEntity(val set: List<CacheSetEntity>)
