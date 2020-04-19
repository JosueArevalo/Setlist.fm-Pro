package com.josuearevalodev.setlistfmpro.screens.searchartists

import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl

class SearchArtistsViewModelImpl() : ViewModel(), SearchArtistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
