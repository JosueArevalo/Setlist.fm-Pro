package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheSetEntity(val song: List<CacheSongEntity>?)

