package com.josuearevalodev.base.classes

sealed class ViewState {

    object Loading : ViewState()

    data class Content<out T : Any>(val value: T) : ViewState()

    data class Error<out T : Throwable>(val error: T) : ViewState()
}
