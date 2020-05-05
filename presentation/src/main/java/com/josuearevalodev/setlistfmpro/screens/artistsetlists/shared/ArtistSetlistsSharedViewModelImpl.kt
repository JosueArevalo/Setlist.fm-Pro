package com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josuearevalodev.domain.location.entities.LocationEntity
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist

class ArtistSetlistsSharedViewModelImpl: ArtistSetlistsSharedViewModel, ViewModel() {

    override val setlists by lazy { MutableLiveData<MutableList<Setlist>>().apply { mutableListOf<Setlist>() } }

    override val userLocation by lazy { MutableLiveData<LocationEntity>() }

    override fun addSetlists(setlistsList: List<Setlist>) {
        val mutableList = mutableListOf<Setlist>()

        // 1 - Current values
        setlists.value?.let {
            mutableList.addAll(it)
        }

        // 2 - New values
        mutableList.addAll(setlistsList)

        setlists.postValue(mutableList)
    }

    override fun clearSetlists() {

    }
}
