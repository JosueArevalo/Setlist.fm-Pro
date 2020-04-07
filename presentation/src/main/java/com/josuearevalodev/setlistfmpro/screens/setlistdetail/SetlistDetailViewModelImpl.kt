package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel

class SetlistDetailViewModelImpl : ViewModel(),SetlistDetailViewModel {

    override fun printHowAreYou() {
        Log.d("TEST", "TEST - How are you?")
    }
}
