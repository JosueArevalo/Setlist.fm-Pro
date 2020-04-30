package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.container.ArtistSetlistsViewModel

class ArtistSetlistsViewModelImpl() : ViewModel(),
    ArtistSetlistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override var artistName: String = ""

    override var currentTab: Int = 0

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}
