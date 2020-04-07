package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel

class SearchArtistsViewModelImpl : ViewModel(), SearchArtistsViewModel {

    override fun printHello() {
        Log.d("TEST", "TEST - Hello world!")
    }
}