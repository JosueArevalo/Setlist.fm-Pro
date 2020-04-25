package com.josuearevalodev.setlistfmpro.screens.searchartists

import androidx.lifecycle.LiveData
import com.josuearevalodev.base.classes.ViewState

interface SetlistDetailViewModel {

    var artistId: String

    var setlistId: String

    val viewState: LiveData<ViewState>

    fun getSetlistDetail()
}
