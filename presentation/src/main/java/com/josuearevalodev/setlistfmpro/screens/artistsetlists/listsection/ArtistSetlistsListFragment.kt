package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.base_android.extensions.visible
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.setlistdetail.navigateToSetlistDetail
import kotlinx.android.synthetic.main.activity_artist_setlists.clContent
import kotlinx.android.synthetic.main.activity_artist_setlists.evError
import kotlinx.android.synthetic.main.activity_artist_setlists.lvLoading
import kotlinx.android.synthetic.main.activity_artist_setlists_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ArtistSetlistsListFragment : Fragment(R.layout.activity_artist_setlists_list) {

    val viewModel: ArtistSetlistsListViewModel by inject()
    val sharedViewModel: ArtistSetlistsSharedViewModel by sharedViewModel<ArtistSetlistsSharedViewModelImpl>()

    val adapter: ArtistSetlistsListAdapter by inject()

    companion object {
        fun createInstance(artistName: String): ArtistSetlistsListFragment {
            val instance = ArtistSetlistsListFragment()
            instance.viewModel.artistName = artistName
            return instance
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareUi()
        addObservers()
        fetchSearchArtist()
    }

    private fun prepareUi() {
        initList()
    }

    private fun addObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is ViewState.Loading -> {
                    clContent.gone()
                    lvLoading.visible()
                    evError.gone()
                }
                is ViewState.Content<*> -> {
                    adapter.addSetlists(viewState.value as List<SetlistEntity>)
                    sharedViewModel.addSetlists(viewState.value as List<SetlistEntity>)
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
            layoutManager = LinearLayoutManager(context)
            addClickListener()
            adapter = this@ArtistSetlistsListFragment.adapter
        }
    }
    
    private fun addClickListener() {
        this@ArtistSetlistsListFragment.adapter.onSetlistClicked = { setlistEntity ->
            context?.navigateToSetlistDetail(
                artistId = setlistEntity.artist.mbid,
                setlistId = setlistEntity.id)
        }
    }

    private fun fetchSearchArtist() {
        viewModel.searchArtistByName(viewModel.artistName)
    }
}
