package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistsByName
import io.reactivex.rxkotlin.addTo

class SearchArtistsViewModelImpl(private val searchArtistsByNamesUseCase: SearchArtistsByName) : ViewModel(), SearchArtistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override fun searchByName(text: String) {
        searchArtistsByNamesUseCase(text)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .subscribe(
                { searchArtistsResponse -> Log.d("TEST", "TEST: Success! $searchArtistsResponse") },
                { error -> Log.e("TEST", "TEST: Error! $error") }
            )
            .addTo(composite)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
