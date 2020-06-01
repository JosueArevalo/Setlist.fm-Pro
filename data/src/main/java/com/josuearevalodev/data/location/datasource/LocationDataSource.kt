package com.josuearevalodev.data.location.datasource

import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Maybe

interface LocationDataSource {

    fun getCurrentLocation(): Maybe<LocationEntity>

}
