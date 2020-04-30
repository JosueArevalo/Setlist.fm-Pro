package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import androidx.lifecycle.LiveData
import com.josuearevalodev.base.classes.ViewState

interface ArtistSetlistsListViewModel {

    var artistName: String

    val viewState: LiveData<ViewState>

    fun searchArtistByName(text: String)

    fun searchSetlists(idArtist: String, page: Int)
}
