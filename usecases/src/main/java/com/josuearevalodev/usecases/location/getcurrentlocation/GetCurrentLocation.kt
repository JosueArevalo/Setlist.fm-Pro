package com.josuearevalodev.usecases.location.getcurrentlocation

import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Single

interface GetCurrentLocation {

    operator fun invoke(): Single<LocationEntity>
}
