package com.josuearevalodev.base

sealed class Result<out S, out F>

data class Success<out S>(val value: S) : Result<S, Nothing>()
data class Failure<out F>(val error: F) : Result<Nothing, F>()
