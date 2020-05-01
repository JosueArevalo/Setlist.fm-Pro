package com.josuearevalodev.data.location.repository

import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.domain.location.repository.LocationRepository
import io.reactivex.Maybe

class LocationRepositoryImpl(
    private val locationDatasource: LocationDataSource
) : LocationRepository {

    override fun getCurrentLocation(): Maybe<LocationEntity> {
        return locationDatasource.getCurrentLocation()
    }
}
