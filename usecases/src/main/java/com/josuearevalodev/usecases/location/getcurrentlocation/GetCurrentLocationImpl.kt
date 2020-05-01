package com.josuearevalodev.usecases.location.getcurrentlocation

import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.domain.location.repository.LocationRepository
import io.reactivex.Maybe

class GetCurrentLocationImpl(
    private val repository: LocationRepository
) : GetCurrentLocation {

    override fun invoke(): Maybe<LocationEntity> {
        return repository.getCurrentLocation()
    }
}
