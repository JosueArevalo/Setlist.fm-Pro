package com.josuearevalodev.setlistfmpro.screens.searchartists

import androidx.lifecycle.LiveData
import com.josuearevalodev.base.classes.ViewState

interface ArtistSetlistsViewModel {

    var artistName: String

    val viewState: LiveData<ViewState>

    fun searchArtistByName(text: String)

    fun searchSetlists(idArtist: String, page: Int)
}