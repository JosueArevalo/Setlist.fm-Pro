package com.josuearevalodev.persistence.mapper

import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.DatabaseArtistEntity

val DatabaseArtistEntity.mapToArtistEntity: ArtistEntity
    get() {
        return ArtistEntity(
            mbid = this.mbid,
            name = this.name ?: "",
            sortName = this.sortName ?: "",
            disambiguation = this.disambiguation ?: "",
            url = this.url ?: ""
        )
    }

val ArtistEntity.mapToDatabaseArtistEntity: DatabaseArtistEntity
    get() {
        return DatabaseArtistEntity(
            mbid = mbid,
            name = name,
            sortName = sortName,
            disambiguation = disambiguation,
            url = url
        )
    }
