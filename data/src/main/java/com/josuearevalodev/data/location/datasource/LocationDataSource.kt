package com.josuearevalodev.data.location.datasource

import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Single

interface LocationDataSource {

    fun getCurrentLocation(): Single<LocationEntity>

    fun isLocationPermissionGranted(): Single<Boolean>
}

