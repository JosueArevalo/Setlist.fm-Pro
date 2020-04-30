package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import android.util.Log
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.usecases.location.getcurrentlocation.GetCurrentLocation
import com.josuearevalodev.usecases.location.islocationpermissiongranted.IsLocationPermissionGranted
import io.reactivex.rxkotlin.addTo

class ArtistSetlistsViewModelImpl(
    private val isLocationPermissionGranted: IsLocationPermissionGranted,
    private val getCurrentLocation: GetCurrentLocation

) : ViewModel(),
    ArtistSetlistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override var artistName: String = ""

    override var currentTab: Int = 0

    override fun handleGetCurrentPosition() {
        isLocationPermissionGranted()
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .doOnSuccess { hasPermissions -> Log.d("TEST", "TEST: doOnSuccess: $hasPermissions")
            }
            .subscribe(
                {
                    Log.d("TEST", "TEST: normal: $it")
                },
                {

                })
            .addTo(composite)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
