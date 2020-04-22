package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.base_android.extensions.visible
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SetlistEntity
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
        addObservers()
        fetchSearchArtist()
    }

    private fun handleIntentData() {
        getArtistNameFromIntent()?.let {
            viewModel.artistName = it
        }
    }

    private fun prepareUi() {
        initList()
    }

    private fun addObservers() {
        viewModel.viewState.observe(this, Observer { viewState ->
            when (viewState) {
                is ViewState.Loading -> {
                    clContent.gone()
                    lvLoading.visible()
                    evError.gone()
                }
                is ViewState.Content<*> -> {
                    adapter.addSetlists(viewState.value as List<SetlistEntity>)
                    clContent.visible()
                    lvLoading.gone()
                    evError.gone()
                }
                is ViewState.Error<*> -> {
                    clContent.gone()
                    lvLoading.gone()
                    evError.visible()
                    Snackbar.make(clContent, "Error: ${viewState.error.cause}", Snackbar.LENGTH_LONG).show()
                }
            }
        })
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
