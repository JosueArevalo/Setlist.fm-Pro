package com.josuearevalodev.data.location.repository

import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.domain.location.repository.LocationRepository
import io.reactivex.Single

class LocationRepositoryImpl(
    private val locationDatasource: LocationDataSource
) : LocationRepository {

    override fun getCurrentLocation(): Single<LocationEntity> {
        return locationDatasource.getCurrentLocation()
    }
}
