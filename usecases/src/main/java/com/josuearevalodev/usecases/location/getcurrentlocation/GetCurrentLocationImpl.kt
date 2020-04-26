package com.josuearevalodev.usecases.location.getcurrentlocation

import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.domain.location.repository.LocationRepository
import io.reactivex.Single

class GetCurrentLocationImpl(
    private val repository: LocationRepository
) : GetCurrentLocation {

    override fun invoke(): Single<LocationEntity> {
        return repository.getCurrentLocation()
    }
}
