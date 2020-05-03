package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.usecases.setlists.getartistsetlists.GetArtistSetlists
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistByName
import io.reactivex.rxkotlin.addTo

class ArtistSetlistsListViewModelImpl(private val searchArtistByNamesUseCase: SearchArtistByName,
                                      private val getArtistSetlistsUseCase: GetArtistSetlists) : ViewModel(),
    ArtistSetlistsListViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override var artistName: String = ""
    override val viewState: MutableLiveData<ViewState> by lazy { MutableLiveData<ViewState>().apply { postValue(ViewState.Loading) } }
    override var currentPage = 1
    override var isLastPage = false
    override val totalPage = 10
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
        searchSetlists(idArtist = artistId, page = currentPage)
    }

    private fun searchSetlists(idArtist: String, page: Int) {
        getArtistSetlistsUseCase(artistId = idArtist, page = page)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .subscribe(
                { setlists ->
                    Log.d("TEST", "TEST: Success! $setlists")
                    viewState.postValue(ViewState.Content(setlists))
                },
                { error ->
                    Log.e("TEST", "TEST: Error! $error")
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
