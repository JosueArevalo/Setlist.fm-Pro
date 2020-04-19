package com.josuearevalodev.persistence.mapper

import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.CacheArtistEntity

val CacheArtistEntity.mapToArtistEntity: ArtistEntity
    get() {
        return ArtistEntity(
            mbid = this.mbid,
            name = this.name ?: "",
            sortName = this.sortName ?: "",
            disambiguation = this.disambiguation ?: "",
            url = this.url ?: ""
        )
    }
