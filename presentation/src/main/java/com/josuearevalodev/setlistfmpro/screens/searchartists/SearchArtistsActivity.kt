package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.container.navigateToArtistSetlists
import kotlinx.android.synthetic.main.activity_search_artists.*

class SearchArtistsActivity : AppCompatActivity(R.layout.activity_search_artists) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareUi()
    }

    private fun prepareUi() {
        handleListeners()
    }

    private fun handleListeners() {
        tilSearch.setEndIconOnClickListener {
            navigateToArtistSetlists(tilSearch.editText?.text.toString().trim())
        }
    }
}
