package com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.josuearevalodev.base.classes.Event
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist

interface ArtistSetlistsSharedViewModel {
    val setlists: LiveData<MutableList<Setlist>>

    val userLocation: MutableLiveData<LocationEntity>

    val showRefreshButton: MutableLiveData<Boolean>

    val refreshMenuClicked: MutableLiveData<Event<Boolean>>

    fun addSetlists(setlists: List<Setlist>)

    fun clearSetlists()
}
