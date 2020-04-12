package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.searchartists.ArtistSetlistsViewModel
import com.josuearevalodev.setlistfmpro.screens.setlistdetail.SetlistDetailActivity
import kotlinx.android.synthetic.main.activity_artist_setlists.*
import org.koin.android.ext.android.inject

class ArtistSetlistsActivity : AppCompatActivity(R.layout.activity_artist_setlists) {

    val viewModel: ArtistSetlistsViewModel by inject()
    val adapter: ArtistSetlistAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntentData()
        prepareUi()
        fetchSearchArtist()
    }

    private fun handleIntentData() {
        getArtistNameFromIntent()?.let {
            viewModel.artistName = it
        }
    }

    private fun prepareUi() {
        initList()
        bNavigate.setOnClickListener {
            startActivity(Intent(this, SetlistDetailActivity::class.java))
        }
    }

    private fun initList() {
        with (rvList) {
            layoutManager = LinearLayoutManager(this@ArtistSetlistsActivity)
            adapter = this@ArtistSetlistsActivity.adapter
        }
    }

    private fun fetchSearchArtist() {
        viewModel.searchArtistByName(viewModel.artistName)
    }
}
