package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.R
import kotlinx.android.synthetic.main.activity_artist_setlists.*
import org.koin.android.ext.android.inject

class ArtistSetlistsActivity : AppCompatActivity(R.layout.activity_artist_setlists) {

    val viewModel: ArtistSetlistsViewModel by inject()

    private lateinit var artistSetlistsPagerAdapter: ArtistSetlistsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleIntentData()
        prepareUi()
    }

    private fun handleIntentData() {
        getArtistNameFromIntent()?.let {
            viewModel.artistName = it
        }
    }

    private fun prepareUi() {
        prepareViewPagerAndTabs()
    }

    private fun prepareViewPagerAndTabs() {

        artistSetlistsPagerAdapter = ArtistSetlistsPagerAdapter(
            supportFragmentManager,
            listOf(
                getString(R.string.tab_artist_setlist_lists_list),
                getString(R.string.tab_artist_setlist_lists_map)
            ),
            viewModel.artistName
        )

        vpArtistSetlists.adapter = artistSetlistsPagerAdapter

        tlTabs.setupWithViewPager(vpArtistSetlists)

        tlTabs.getTabAt(0)?.setIcon(R.drawable.ic_list)
        tlTabs.getTabAt(1)?.setIcon(R.drawable.ic_map)
    }
}
