package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josuearevalodev.domain.location.entities.LocationEntity

abstract class ArtistSetlistsViewModel : ViewModel()  {

    abstract var userLocation: MutableLiveData<LocationEntity>?

    abstract var artistName: String

    abstract var currentTab: Int

    abstract fun requestCurrentLocation()
}
