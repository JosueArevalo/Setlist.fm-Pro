package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base.classes.ViewState

abstract class ArtistSetlistsListViewModel : ViewModel() {

    abstract var artistName: String

    abstract val viewState: LiveData<ViewState>

    // Pagination

    abstract var currentPage: Int

    abstract var itemsPerPage: Int

    abstract val firstPage: Int

    abstract var isLastPage: Boolean

    abstract val totalPages: Int

    abstract var isLoading: Boolean

    abstract var itemCount: Int

    //region functions

    abstract fun searchArtistByName(text: String)

    abstract fun loadMoreItems()

    //endregion
}
