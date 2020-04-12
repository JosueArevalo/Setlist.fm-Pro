package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.usecases.setlists.getartistsetlists.GetArtistSetlists
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistsByName
import io.reactivex.rxkotlin.addTo

class ArtistSetlistsViewModelImpl(private val searchArtistsByNamesUseCase: SearchArtistsByName,
                                  private val getArtistSetlistsUseCase: GetArtistSetlists) : ViewModel(), ArtistSetlistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override var artistName: String = ""
    private var artistSetlistPage = 1

    override fun searchArtistByName(text: String) {
        searchArtistsByNamesUseCase(text)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .subscribe(
                { searchArtistsResponse ->
                    Log.d("TEST", "TEST: Success! $searchArtistsResponse")
                    searchArtistsResponse.artist.firstOrNull()?.let { artist ->
                        searchSetlists(artist.mbid, artistSetlistPage)
                    }
                },
                { error -> Log.e("TEST", "TEST: Error! $error") }
            )
            .addTo(composite)
    }

    override fun searchSetlists(idArtist: String, page: Int) {
    getArtistSetlistsUseCase(artistId = idArtist, page = page)
        .subscribeOn(ioThread)
        .observeOn(mainThread)
        .subscribe(
            { artistSetlistsResponse -> Log.d("TEST", "TEST: Success! $artistSetlistsResponse") },
            { error -> Log.e("TEST", "TEST: Error! $error")}
        )
        .addTo(composite)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
