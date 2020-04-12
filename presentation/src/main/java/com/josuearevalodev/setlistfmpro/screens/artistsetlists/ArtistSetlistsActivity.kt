package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        prepareUi()
        handleIntentData()
    }

    private fun handleIntentData() {
        getArtistNameFromIntent()?.let {
            Log.d("TEST", "TEST: Received $it")
            viewModel.artistName = it
        }
    }

    private fun prepareUi() {
        initList()
        bNavigate.setOnClickListener {
            startActivity(Intent(this, SetlistDetailActivity::class.java))
        }

        viewModel.searchSetlists(idArtist = "c4707a18-2236-4426-9e67-429ce023777c",
            page = 1)
    }

    private fun initList() {
        with (rvList) {
            layoutManager = LinearLayoutManager(this@ArtistSetlistsActivity)
            adapter = this@ArtistSetlistsActivity.adapter
        }
    }
}
