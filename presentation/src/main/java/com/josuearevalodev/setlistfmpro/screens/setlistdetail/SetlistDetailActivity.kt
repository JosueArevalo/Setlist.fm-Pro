package com.josuearevalodev.setlistfmpro.screens.setlistdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.base_android.extensions.visible
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist
import com.josuearevalodev.setlistfmpro.R
import kotlinx.android.synthetic.main.activity_setlist_detail.*
import kotlinx.android.synthetic.main.inc_setlist_detail_card.*
import org.koin.android.ext.android.inject

class SetlistDetailActivity : AppCompatActivity(R.layout.activity_setlist_detail) {

    val viewModel: SetlistDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntentData()
        addObservers()
        fetchSetlistDetail()
    }

    private fun handleIntentData() {
        viewModel.artistId = getArtistIdFromIntent
        viewModel.setlistId = getSetlistIdFromIntent
    }

    private fun addObservers() {
        viewModel.viewState.observe(this, Observer { viewState ->
            when (viewState) {
                is ViewState.Loading -> {
                    svContent.gone()
                    lvLoading.visible()
                    evError.gone()
                }
                is ViewState.Content<*> -> {
                    (viewState.value as Setlist).prepareUi()
                    svContent.visible()
                    lvLoading.gone()
                    evError.gone()
                }
                is ViewState.Error<*> -> {
                    svContent.gone()
                    lvLoading.gone()
                    evError.visible()
                    Snackbar.make(svContent, "Error: ${viewState.error.cause}", Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun fetchSetlistDetail() {
        viewModel.getSetlistDetail()
    }

    private fun Setlist.prepareUi() {
        var cell: View? = null
        this.sets.set.forEach { setEntity ->
            setEntity.song.forEach {song ->
                cell = layoutInflater.inflate(R.layout.cell_songlist_song, llSongList, false)
                llSongList.addView(cell)

                (cell?.findViewById<MaterialTextView>(R.id.tvSongName))?.text = song.name
            }
        }
    }
}
