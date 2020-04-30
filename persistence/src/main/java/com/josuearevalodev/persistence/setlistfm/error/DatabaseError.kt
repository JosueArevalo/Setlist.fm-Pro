package com.josuearevalodev.persistence.setlistfm.error

import androidx.room.EmptyResultSetException

sealed class DatabaseError : Throwable() {

    data class NoResultsFound(override val cause: Throwable? = null) : DatabaseError()

    data class Unexpected(override val cause: Throwable? = null) : DatabaseError()
}

val Throwable.mapToDatabaseError : DatabaseError
get() {
    return when (this) {
        is EmptyResultSetException -> DatabaseError.NoResultsFound(this)
        else -> DatabaseError.Unexpected(this)
    }
}
