package com.josuearevalodev.location.location.datasource

import android.location.LocationManager
import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.location.location.mapper.mapToLocationEntity
import io.reactivex.Maybe
import ru.solodovnikov.rx2locationmanager.RxLocationManager

class LocationDataSourceImpl(
    private val rxLocationManager: RxLocationManager
) : LocationDataSource {

    override fun getCurrentLocation(): Maybe<LocationEntity> {
        return rxLocationManager.getLastLocation(LocationManager.NETWORK_PROVIDER)
            .map { it.mapToLocationEntity }
    }
}
