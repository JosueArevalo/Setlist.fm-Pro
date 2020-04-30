package com.josuearevalodev.location.location.datasource

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.josuearevalodev.data.location.datasource.LocationDataSource
import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Single

class LocationDataSourceImpl(private val application: Application) : LocationDataSource {

    override fun getCurrentLocation(): Single<LocationEntity> {
        return Single.just(LocationEntity(40f, 2f)) // TODO
    }

    override fun isLocationPermissionGranted(): Single<Boolean> {
        return Single.just(ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    }
}
