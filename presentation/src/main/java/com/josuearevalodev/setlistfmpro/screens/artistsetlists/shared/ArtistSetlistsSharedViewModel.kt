package com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared

import androidx.lifecycle.LiveData
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity

interface ArtistSetlistsSharedViewModel {
    val setlists: LiveData<MutableList<SetlistEntity>>

    fun addSetlists(setlists: List<SetlistEntity>)

    fun clearSetlists()
}
