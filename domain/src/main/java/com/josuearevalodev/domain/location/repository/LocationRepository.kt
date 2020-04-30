package com.josuearevalodev.domain.location.repository

import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Single

interface LocationRepository {

    fun getCurrentLocation(): Single<LocationEntity>

    fun isLocationPermissionGranted(): Single<Boolean>
}
