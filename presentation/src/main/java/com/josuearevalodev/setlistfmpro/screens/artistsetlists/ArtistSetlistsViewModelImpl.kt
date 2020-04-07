package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel

class ArtistSetlistsViewModelImpl : ViewModel(), ArtistSetlistsViewModel {

    override fun printBye() {
        Log.d("TEST", "TEST - Bye bye!")
    }
}