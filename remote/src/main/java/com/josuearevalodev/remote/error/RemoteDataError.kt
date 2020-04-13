package com.josuearevalodev.remote.error

import io.reactivex.Single
import io.reactivex.SingleSource
import retrofit2.HttpException

sealed class RemoteDataError : Throwable()

data class BadRequest(override val cause: Throwable? = null) : RemoteDataError() // 400

data class Unauthorized(override val cause: Throwable? = null) : RemoteDataError() // 401

data class NotFound(override val cause: Throwable? = null) : RemoteDataError() // 404

data class TooManyRequests(override val cause: Throwable? = null) : RemoteDataError() // 429

data class Unexpected(override val cause: Throwable? = null) : RemoteDataError()

val HttpException.toRemoteDataError: SingleSource<out Any>
    get() {
        return when (code()) {
            400 -> Single.error(BadRequest(this))
            401 -> Single.error(Unauthorized(this))
            404 -> Single.error(NotFound(this))
            429 -> Single.error(TooManyRequests(this))
            else -> Single.error(Unexpected(this))
        }
    }
