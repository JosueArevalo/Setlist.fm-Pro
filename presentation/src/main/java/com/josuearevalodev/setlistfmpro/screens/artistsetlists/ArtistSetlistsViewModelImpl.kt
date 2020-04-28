package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl

class ArtistSetlistsViewModelImpl() : ViewModel(), ArtistSetlistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override var artistName: String = ""

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
