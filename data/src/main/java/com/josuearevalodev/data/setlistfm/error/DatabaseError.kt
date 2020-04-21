package com.josuearevalodev.data.setlistfm.error

sealed class DatabaseError : Throwable() {

    data class NoResultsFound(override val cause: Throwable? = null) : DatabaseError()

    data class Unexpected(override val cause: Throwable? = null) : DatabaseError()

}