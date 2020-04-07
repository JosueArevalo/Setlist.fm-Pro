package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.ArtistSetlistsActivity
import kotlinx.android.synthetic.main.activity_search_artists.*

class SearchArtistsActivity : AppCompatActivity(R.layout.activity_search_artists) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bNavigate.setOnClickListener {
            startActivity(Intent(this, ArtistSetlistsActivity::class.java))
        }
    }
}
