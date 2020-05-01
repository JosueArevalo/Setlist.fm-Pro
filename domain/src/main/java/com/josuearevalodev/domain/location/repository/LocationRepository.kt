package com.josuearevalodev.domain.location.repository

import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Maybe

interface LocationRepository {

    fun getCurrentLocation(): Maybe<LocationEntity>

}
