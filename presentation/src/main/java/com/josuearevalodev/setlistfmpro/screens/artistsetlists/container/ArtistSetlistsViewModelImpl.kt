package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.usecases.location.getcurrentlocation.GetCurrentLocation
import io.reactivex.rxkotlin.addTo

class ArtistSetlistsViewModelImpl(
    private val getCurrentLocation: GetCurrentLocation
) : ViewModel(),
    ArtistSetlistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override var userLocation: MutableLiveData<LocationEntity>? = null

    override var artistName: String = ""

    override var currentTab: Int = 0

    override fun requestCurrentLocation() {
        getCurrentLocation().subscribe { location ->
            userLocation?.postValue(location)
        }.addTo(composite)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
