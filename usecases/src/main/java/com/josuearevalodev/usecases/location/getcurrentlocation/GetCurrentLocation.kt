package com.josuearevalodev.usecases.location.getcurrentlocation

import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Maybe

interface GetCurrentLocation {

    operator fun invoke(): Maybe<LocationEntity>
}
