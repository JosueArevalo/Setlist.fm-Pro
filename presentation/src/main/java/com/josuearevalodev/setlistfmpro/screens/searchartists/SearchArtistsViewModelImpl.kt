package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base.Failure
import com.josuearevalodev.base.Success
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistsByName
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SearchArtistsViewModelImpl(private val searchArtistsByNamesUseCase: SearchArtistsByName) : ViewModel(), SearchArtistsViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override fun searchByName(text: String) {
        searchArtistsByNamesUseCase(text)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .doOnSuccess { result ->
                when (result) {
                    is Success -> { Log.d("TEST", "TEST: Success: ${result.value}")}
                    is Failure -> { Log.d("TEST", "TEST: Failure: ${result.error}")}
                    }
            }
            .doOnError {
                Log.d("TEST", "TEST: Error: $it")
            }
            .subscribe()
            .addTo(composite)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}