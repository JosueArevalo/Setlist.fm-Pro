package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import androidx.lifecycle.LiveData
import com.josuearevalodev.base.classes.ViewState

interface ArtistSetlistsListViewModel {

    var artistName: String

    val viewState: LiveData<ViewState>

    // Pagination

    var currentPage: Int

    var itemsPerPage: Int

    val firstPage: Int

    var isLastPage: Boolean

    val totalPages: Int

    var isLoading: Boolean

    var itemCount: Int

    //region functions

    fun searchArtistByName(text: String)

    fun loadMoreItems()

    //endregion
}
