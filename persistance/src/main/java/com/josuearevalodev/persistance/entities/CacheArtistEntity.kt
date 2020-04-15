package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheArtistEntity(val mbid: String?,
                             val name: String?,
                             val sortName: String?,
                             val disambiguation: String?,
                             val url: String?) {
}
