package com.josuearevalodev.setlistfmpro.screens.searchartists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.databinding.ActivitySearchArtistsBinding
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.container.navigateToArtistSetlists

class SearchArtistsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchArtistsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchArtistsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareUi()
    }

    private fun prepareUi() {
        handleListeners()
    }

    private fun handleListeners() {
        binding.tilSearch.setEndIconOnClickListener {
            navigateToArtistSetlists(binding.tilSearch.editText?.text.toString().trim())
        }
    }
}
