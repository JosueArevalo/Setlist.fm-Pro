package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.ArtistSetlistsActivity
import kotlinx.android.synthetic.main.activity_search_artists.*
import org.koin.android.ext.android.inject

class SearchArtistsActivity : AppCompatActivity(R.layout.activity_search_artists) {

    val viewModel: SearchArtistsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.printHello()

        bNavigate.setOnClickListener {
            startActivity(Intent(this, ArtistSetlistsActivity::class.java))
        }
    }
}
