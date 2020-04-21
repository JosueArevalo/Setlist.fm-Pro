package com.josuearevalodev.data.setlistfm.error

sealed class RemoteError : Throwable()

data class BadRequest(override val cause: Throwable? = null) : RemoteError() // 400

data class Unauthorized(override val cause: Throwable? = null) : RemoteError() // 401

data class NotFound(override val cause: Throwable? = null) : RemoteError() // 404

data class TooManyRequests(override val cause: Throwable? = null) : RemoteError() // 429

data class Unexpected(override val cause: Throwable? = null) : RemoteError()
