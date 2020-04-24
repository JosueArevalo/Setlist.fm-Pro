package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.util.Log
import androidx.lifecycle.ViewModel
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManager
import com.josuearevalodev.base_android.rxdisposablemanager.RxDisposableManagerImpl
import com.josuearevalodev.usecases.setlists.getsetlistdetail.GetSetlistDetail
import io.reactivex.rxkotlin.addTo

class SetlistDetailViewModelImpl(
    private val getSetlistDetailUC: GetSetlistDetail
) : ViewModel(), SetlistDetailViewModel, RxDisposableManager by RxDisposableManagerImpl() {

    override lateinit var artistId: String
    override lateinit var setlistId: String

    override fun getSetlistDetail() {
        getSetlistDetailUC(setlistId = setlistId)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .subscribe({ setlist ->
                Log.d("TEST", "TEST: Success! $setlist")

            },
                { error ->
                    Log.d("TEST", "TEST: Error! $error")

                })
            .addTo(composite)
    }
}
