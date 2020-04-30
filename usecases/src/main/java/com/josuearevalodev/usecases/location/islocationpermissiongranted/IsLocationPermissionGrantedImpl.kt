package com.josuearevalodev.usecases.location.islocationpermissiongranted

import com.josuearevalodev.domain.location.repository.LocationRepository
import io.reactivex.Single

class IsLocationPermissionGrantedImpl(
    private val repository: LocationRepository
) : IsLocationPermissionGranted {

    override fun invoke(): Single<Boolean> {
        return repository.isLocationPermissionGranted()
    }
}
