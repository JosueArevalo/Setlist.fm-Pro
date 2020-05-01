package com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity

interface ArtistSetlistsSharedViewModel {
    val setlists: LiveData<MutableList<SetlistEntity>>

    val userLocation: MutableLiveData<LocationEntity>

    fun addSetlists(setlists: List<SetlistEntity>)

    fun clearSetlists()
}
