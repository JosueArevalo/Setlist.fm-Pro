package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import androidx.lifecycle.MutableLiveData
import com.josuearevalodev.domain.location.entities.LocationEntity

interface ArtistSetlistsViewModel {

    var userLocation: MutableLiveData<LocationEntity>?

    var artistName: String

    var currentTab: Int

    fun requestCurrentLocation()
}
