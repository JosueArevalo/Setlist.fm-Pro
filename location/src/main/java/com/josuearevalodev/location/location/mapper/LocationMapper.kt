package com.josuearevalodev.location.location.mapper

import android.location.Location
import com.josuearevalodev.domain.location.entities.LocationEntity

val Location.mapToLocationEntity
    get() = LocationEntity(
        longitude = longitude.toFloat(),
        latitude = latitude.toFloat())
