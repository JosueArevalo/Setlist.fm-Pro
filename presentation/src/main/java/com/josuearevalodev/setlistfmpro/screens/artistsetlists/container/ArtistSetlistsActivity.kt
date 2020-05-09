package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.josuearevalodev.base.classes.Event
import com.josuearevalodev.base_android.extensions.tintMenuItem
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.commons.PermissionRequester
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.*
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_artist_setlists.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ArtistSetlistsActivity : AppCompatActivity(R.layout.activity_artist_setlists),
    PermissionListener, PermissionRequestErrorListener {

    val viewModel: ArtistSetlistsViewModel by inject()
    val sharedViewModel: ArtistSetlistsSharedViewModel by viewModel<ArtistSetlistsSharedViewModelImpl>()

    private lateinit var artistSetlistsPagerAdapter: ArtistSetlistsPagerAdapter

    private val permissionRequester: PermissionRequester by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleIntentData()
        handleSharedVariables()
        prepareUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        when {
            (viewModel.currentTab == 0 && sharedViewModel.showRefreshButton.value == true) -> { // Show refresh icon in tab 0 when it's needed
                menuInflater.inflate(R.menu.menu_setlists_list, menu)
                menu?.tintMenuItem(R.id.action_refresh, Color.WHITE)
            }

            (viewModel.currentTab == 1) -> { // Show gps icon in tab 1.
                menuInflater.inflate(R.menu.menu_setlists_map, menu)
                menu?.tintMenuItem(R.id.action_gps, Color.WHITE)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_gps -> {
                onGpsClicked()
                return true
            }

            R.id.action_refresh -> {
                sharedViewModel.refreshMenuClicked.postValue(Event(true))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //private

    private fun handleIntentData() {
        getArtistNameFromIntent()?.let {
            viewModel.artistName = it
        }
    }

    private fun handleSharedVariables() {
        viewModel.userLocation = sharedViewModel.userLocation
    }

    private fun prepareUi() {
        addObservers()
        prepareViewPagerAndTabs()
    }

    private fun addObservers() {
        sharedViewModel.showRefreshButton.observe(this, Observer {
            invalidateOptionsMenu()
        })
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

        vpArtistSetlists.addOnPageChangeListener(object : OnTabChanged {
            override fun onPageSelected(position: Int) {
                viewModel.currentTab = position
                invalidateOptionsMenu()
            }
        })
    }

    private fun onGpsClicked() {
        permissionRequester.request(
            permission =  Manifest.permission.ACCESS_COARSE_LOCATION,
            permissionListener = this,
            permissionRequestErrorListener = this
        )
    }

    //region Permissions
    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        viewModel.requestCurrentLocation()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {}

    override fun onPermissionRationaleShouldBeShown(request: PermissionRequest?, token: PermissionToken?) {
        token?.continuePermissionRequest()
    }

    override fun onError(error: DexterError?) {}
    //endregion
}
