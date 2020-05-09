package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.base_android.extensions.visible
import com.josuearevalodev.base_android.recyclerview.PaginationListener
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.setlistdetail.navigateToSetlistDetail
import kotlinx.android.synthetic.main.activity_artist_setlists.clContent
import kotlinx.android.synthetic.main.activity_artist_setlists.evError
import kotlinx.android.synthetic.main.activity_artist_setlists.lvLoading
import kotlinx.android.synthetic.main.activity_artist_setlists_list.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ArtistSetlistsListFragment : Fragment(R.layout.activity_artist_setlists_list) {

    private val viewModel: ArtistSetlistsListViewModel by inject()
    private val sharedViewModel: ArtistSetlistsSharedViewModel by sharedViewModel<ArtistSetlistsSharedViewModelImpl>()
    private val adapter: ArtistSetlistsListAdapter by inject()

    private val listHasItems: Boolean
        get() = adapter.setlistsSize > 0


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
        addListeners()
    }

    private fun addListeners() {
        bRetry.setOnClickListener {
            handleRetry()
        }
    }

    private fun handleRetry() {
        if (!listHasItems) {
            viewModel.searchArtistByName(viewModel.artistName)
        } else {
            viewModel.loadMoreItems()
        }
    }

    private fun addObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is ViewState.Loading -> {
                    if (!listHasItems) {
                        clContent.gone()
                        lvLoading.visible()
                        evError.gone()
                    } else {
                        clContent.visible()
                        lvLoading.gone()
                        evError.gone()
                    }
                }
                is ViewState.Content<*> -> {

                    adapter.removeLoading()

                    adapter.addSetlists(viewState.value as List<Setlist>)
                    sharedViewModel.addSetlists(viewState.value as List<Setlist>)

                    if (viewModel.currentPage < viewModel.totalPages) {
                        adapter.addLoading()
                    } else {
                        viewModel.isLastPage = true
                    }

                    viewModel.isLoading = false

                    clContent.visible()
                    lvLoading.gone()
                    evError.gone()
                }
                is ViewState.Error<*> -> {
                    viewState.handleViewStateError()
                }
            }
        })

        sharedViewModel.refreshMenuClicked.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled {
                sharedViewModel.showRefreshButton.postValue(false)
                handleRetry()
            }
        })
    }

    private fun ViewState.Error<*>.handleViewStateError() {
        if (!listHasItems) {
            clContent.gone()
            lvLoading.gone()
            evError.visible()
        } else {
            clContent.visible()
            lvLoading.gone()
            evError.gone()

            sharedViewModel.showRefreshButton.postValue(true)
            adapter.removeLoading()
            Snackbar.make(
                clContent,
                getString(R.string.setlist_list_error_in_next_pages), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun initList() {
        with (rvList) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addClickListener()
            adapter = this@ArtistSetlistsListFragment.adapter

            addOnScrollListener(object: PaginationListener(layoutManager as LinearLayoutManager) {

                override val itemsPerPage: Int
                    get() = viewModel.itemsPerPage

                override val firstPage: Int
                    get() = viewModel.firstPage

                override val isLoading: Boolean
                    get() = viewModel.isLoading

                override val isLastPage: Boolean
                    get() = viewModel.isLastPage

                override fun loadMoreItems() {
                    viewModel.loadMoreItems()
                }
            })
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
