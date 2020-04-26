package com.josuearevalodev.usecases.location.getcurrentlocation

sealed class GetCurrentLocationError : Throwable() {

    object PermissionsNotGranted : GetCurrentLocationError()
}
