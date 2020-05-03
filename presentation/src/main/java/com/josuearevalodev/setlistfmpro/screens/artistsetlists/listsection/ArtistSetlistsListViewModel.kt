package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import androidx.lifecycle.LiveData
import com.josuearevalodev.base.classes.ViewState

interface ArtistSetlistsListViewModel {

    var artistName: String

    val viewState: LiveData<ViewState>

    var currentPage: Int

    var isLastPage: Boolean

    val totalPage: Int

    var isLoading: Boolean

    var itemCount: Int

    fun searchArtistByName(text: String)

    fun loadMoreItems()
}
