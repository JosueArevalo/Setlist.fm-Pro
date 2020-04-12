package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistsByName
import io.reactivex.rxkotlin.addTo

class SearchArtistsViewModelImpl() : ViewModel(), SearchArtistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
