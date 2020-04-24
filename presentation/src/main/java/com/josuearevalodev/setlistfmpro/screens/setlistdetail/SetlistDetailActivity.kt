package com.josuearevalodev.setlistfmpro.screens.setlistdetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.searchartists.SetlistDetailViewModel
import org.koin.android.ext.android.inject

class SetlistDetailActivity : AppCompatActivity(R.layout.activity_setlist_detail) {

    val viewModel: SetlistDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.artistId = getArtistIdFromIntent
        viewModel.setlistId = getSetlistIdFromIntent
        viewModel.getSetlistDetail()

    }
}
