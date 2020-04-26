package com.josuearevalodev.location.location.datasource

import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Single

class LocationDataSourceImpl : LocationDataSource {

    override fun getCurrentLocation(): Single<LocationEntity> {
        return Single.just(LocationEntity(40f, 2f))
    }
}
