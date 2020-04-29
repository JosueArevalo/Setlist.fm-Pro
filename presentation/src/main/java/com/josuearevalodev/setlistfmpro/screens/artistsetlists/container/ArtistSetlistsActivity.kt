package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import kotlinx.android.synthetic.main.activity_artist_setlists.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel

class ArtistSetlistsActivity : AppCompatActivity(R.layout.activity_artist_setlists) {

    val viewModel: ArtistSetlistsViewModel by inject()
    val sharedViewModel: ArtistSetlistsSharedViewModel by viewModel<ArtistSetlistsSharedViewModelImpl>()

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

        artistSetlistsPagerAdapter =
            ArtistSetlistsPagerAdapter(
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
