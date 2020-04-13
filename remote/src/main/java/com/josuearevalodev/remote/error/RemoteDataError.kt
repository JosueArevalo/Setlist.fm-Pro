package com.josuearevalodev.remote.error

sealed class RemoteDataError : Throwable()

data class BadRequest(override val cause: Throwable? = null) : RemoteDataError() // 400

data class Unauthorized(override val cause: Throwable? = null) : RemoteDataError() // 401

data class NotFound(override val cause: Throwable? = null) : RemoteDataError() // 404

data class TooManyRequests(override val cause: Throwable? = null) : RemoteDataError() // 429

data class Unexpected(override val cause: Throwable? = null) : RemoteDataError()
