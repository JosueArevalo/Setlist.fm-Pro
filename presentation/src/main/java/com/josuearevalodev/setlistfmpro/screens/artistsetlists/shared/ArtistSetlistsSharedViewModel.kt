package com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist

interface ArtistSetlistsSharedViewModel {
    val setlists: LiveData<MutableList<Setlist>>

    val userLocation: MutableLiveData<LocationEntity>

    fun addSetlists(setlists: List<Setlist>)

    fun clearSetlists()
}
