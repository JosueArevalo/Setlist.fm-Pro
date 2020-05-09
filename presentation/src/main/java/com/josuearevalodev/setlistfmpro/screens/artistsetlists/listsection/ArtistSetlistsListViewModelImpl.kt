package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.setlistfmpro.models.setlistfm.mapToArtistSetlistsResponse
import com.josuearevalodev.usecases.setlists.getartistsetlists.GetArtistSetlists
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistByName
import io.reactivex.rxkotlin.addTo

class ArtistSetlistsListViewModelImpl(private val searchArtistByNamesUseCase: SearchArtistByName,
                                      private val getArtistSetlistsUseCase: GetArtistSetlists) : ViewModel(),
    ArtistSetlistsListViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override var artistName: String = ""
    override val viewState: MutableLiveData<ViewState> by lazy { MutableLiveData<ViewState>().apply { postValue(ViewState.Loading) } }
    override var currentPage = 1
    override var itemsPerPage = 0
    override val firstPage = 1
    override var isLastPage = false
    override var totalPages = 0
    override var isLoading = false
    override var itemCount = 0

    private lateinit var artistId: String

    override fun searchArtistByName(text: String) {
        viewState.postValue(ViewState.Loading)
        searchArtistByNamesUseCase(text)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .subscribe(
                { artist ->
                    Log.d("TEST", "TEST: Success! $artist")
                    artistId = artist.mbid
                    searchSetlists(artist.mbid, currentPage)
                },
                { error ->
                    Log.e("TEST", "TEST: Error! $error")
                    viewState.postValue(ViewState.Error(error))
                }
            )
            .addTo(composite)
    }

    override fun loadMoreItems() {
        isLoading = true
        currentPage++
        searchSetlists(idArtist = artistId, page = currentPage, itemsPerPage = itemsPerPage)
    }

    private fun searchSetlists(idArtist: String, page: Int = 1, itemsPerPage: Int = 20) {
        Log.d("TEST", "TEST: searchSetlists: Page: $page & itemsPerPage: $itemsPerPage")

        getArtistSetlistsUseCase(artistId = idArtist, page = page, itemsPerPage = itemsPerPage)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .map { artistSetlistResponseEntity ->
                artistSetlistResponseEntity.mapToArtistSetlistsResponse
            }
            .subscribe(
                { artistSetlistsResponse ->
                    Log.d("TEST", "TEST: Success! Setlists found: ${artistSetlistsResponse.setlist.size}")
                    this.itemsPerPage = artistSetlistsResponse.itemsPerPage
                    totalPages = Math.ceil(artistSetlistsResponse.total.toDouble() / artistSetlistsResponse.itemsPerPage).toInt()

                    viewState.postValue(ViewState.Content(artistSetlistsResponse.setlist))
                },
                { error ->
                    Log.e("TEST", "TEST: Error! $error")
                    // If is in first page (because Search Artist failed e.g. it hasn't do decrease currentPage
                    if (currentPage > 1) {
                        currentPage--
                    }
                    viewState.postValue(ViewState.Error(Throwable()))
                }
            )
            .addTo(composite)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
