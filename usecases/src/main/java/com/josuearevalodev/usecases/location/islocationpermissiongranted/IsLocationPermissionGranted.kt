package com.josuearevalodev.usecases.location.islocationpermissiongranted

import com.josuearevalodev.domain.location.entities.LocationEntity
import io.reactivex.Single

interface IsLocationPermissionGranted {

    operator fun invoke(): Single<Boolean>
}
