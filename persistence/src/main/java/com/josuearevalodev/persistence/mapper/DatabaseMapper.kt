package com.josuearevalodev.persistence.mapper

import androidx.room.EmptyResultSetException
import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.DatabaseArtistEntity
import com.josuearevalodev.persistence.error.DatabaseError

//region entities
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
//endregion

//region error
val Throwable.mapToDatabaseError : DatabaseError
    get() {
        return when (this) {
            is EmptyResultSetException -> DatabaseError.NoResultsFound(this)
            else -> DatabaseError.Unexpected(this)
        }
    }
//endregion
