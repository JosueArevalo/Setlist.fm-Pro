package com.josuearevalodev.setlistfmpro.screens.setlistdetail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.base_android.extensions.visible
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.databinding.ActivitySetlistDetailBinding
import org.koin.android.ext.android.inject

class SetlistDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetlistDetailBinding
    private val viewModel: SetlistDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySetlistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    binding.svContent.gone()
                    binding.lvLoading.visible()
                    binding.evError.gone()
                }
                is ViewState.Content<*> -> {
                    (viewState.value as Setlist).prepareUi()
                    binding.svContent.visible()
                    binding.lvLoading.gone()
                    binding.evError.gone()
                }
                is ViewState.Error<*> -> {
                    binding.svContent.gone()
                    binding.lvLoading.gone()
                    binding.evError.visible()
                    Snackbar.make(binding.svContent, "Error: ${viewState.error.cause}", Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun fetchSetlistDetail() {
        viewModel.getSetlistDetail()
    }

    private fun Setlist.prepareUi() {
        prepareHeader()
        prepareSonglist()
    }

    private fun Setlist.prepareHeader() {
        binding.clHeader.dvDate.tvMonth.text = month
        binding.clHeader.dvDate.tvDay.text = day
        binding.clHeader.dvDate.tvYear.text = year

        binding.clHeader.tvTitle.text = "${artist.name} Setlist"

        val locationHtmlText = "at <a href=\"\">${venue.name}, ${venue.city.name}, ${venue.city.country.name}</a>"
        binding.clHeader.tvLocation.setHtmlText(locationHtmlText)

        with(tour.name) {
            when (isEmpty()) {
                true -> binding.clHeader.tvTour.gone()
                false -> {
                    val tourHtmlText = "Tour: <a href=\"\">$this</a>"
                    binding.clHeader.tvTour.setHtmlText(tourHtmlText)
                }
            }
        }
    }

    private fun TextView.setHtmlText(htmlText: String) {
        text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(htmlText)
        }
    }

    private fun Setlist.prepareSonglist() {
        var cell: View? = null

        var songCount = 1
        val llSongList = binding.cvSetlist.llSongList
        this.sets.set.forEachIndexed { setIndex, setEntity ->

            when {
                setEntity.name.isNotEmpty() -> {
                    cell = layoutInflater.inflate(R.layout.cell_songlist_encore, llSongList, false)
                    llSongList.addView(cell)

                    (cell?.findViewById<MaterialTextView>(R.id.tvEncoreSet))?.text = "${setEntity.name}:"
                }

                setEntity.encore > 0 -> {
                    cell = layoutInflater.inflate(R.layout.cell_songlist_encore, llSongList, false)
                    llSongList.addView(cell)

                    (cell?.findViewById<MaterialTextView>(R.id.tvEncoreSet))?.text = "Encore:"
                }
            }

            setEntity.song.forEachIndexed { songIndex, song ->
                when {
                    song.tape -> {
                        cell = layoutInflater.inflate(R.layout.cell_songlist_tape, llSongList, false)
                        llSongList.addView(cell)

                        (cell?.findViewById<MaterialTextView>(R.id.tvTapeName))?.text = if (songIndex == 0) "Intro" else "Outro"
                    }

                    !song.tape && song.info.isEmpty() -> {
                        cell = layoutInflater.inflate(R.layout.cell_songlist_song, llSongList, false)
                        llSongList.addView(cell)

                        (cell?.findViewById<MaterialTextView>(R.id.tvSongName))?.text = song.name
                        (cell?.findViewById<MaterialTextView>(R.id.tvSongNumber))?.text = songCount.toString()
                        songCount++
                    }

                    !song.tape && song.info.isNotEmpty() -> {
                        cell = layoutInflater.inflate(R.layout.cell_songlist_song_with_info, llSongList, false)
                        llSongList.addView(cell)

                        (cell?.findViewById<MaterialTextView>(R.id.tvSongName))?.text = song.name
                        (cell?.findViewById<MaterialTextView>(R.id.tvSongInfo))?.text = "(${song.info})"
                        (cell?.findViewById<MaterialTextView>(R.id.tvSongNumber))?.text = songCount.toString()
                        songCount++
                    }
                }
            }
        }
    }
}
