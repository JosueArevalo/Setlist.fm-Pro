package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.josuearevalodev.base.classes.ViewState
import com.josuearevalodev.base_android.extensions.gone
import com.josuearevalodev.base_android.extensions.visible
import com.josuearevalodev.base_android.recyclerview.PaginationListener
import com.josuearevalodev.data.setlistfm.error.NoInternetConnection
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.databinding.FragmentArtistSetlistsListBinding
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.setlistdetail.navigateToSetlistDetail
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ArtistSetlistsListFragment : Fragment(R.layout.fragment_artist_setlists_list) {

    private var _binding: FragmentArtistSetlistsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArtistSetlistsListViewModel by viewModel()
    private val sharedViewModel: ArtistSetlistsSharedViewModel by sharedViewModel<ArtistSetlistsSharedViewModelImpl>()

    private val adapter: ArtistSetlistsListAdapter by inject()

    private val listHasItems: Boolean
        get() = adapter.setlistsSize > 0

    private val SAVED_ARTIST_NAME = "artistNameSaved"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.artistName = arguments?.getString("artistName", "") ?: ""
        } else {
            viewModel.artistName = savedInstanceState.getString(SAVED_ARTIST_NAME, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentArtistSetlistsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareUi()
        addObservers()
        fetchSearchArtist()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SAVED_ARTIST_NAME, viewModel.artistName)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun prepareUi() {
        initList()
        addListeners()
    }

    private fun addListeners() {
        binding.evError.bRetry.setOnClickListener {
            handleRetry()
        }

        binding.ncNoConnection.bRetry.setOnClickListener {
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
                        binding.clContent.gone()
                        binding.lvLoading.visible()
                        binding.evError.gone()
                        binding.ncNoConnection.gone()
                    } else {
                        binding.clContent.visible()
                        binding.lvLoading.gone()
                        binding.evError.gone()
                        binding.ncNoConnection.gone()
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

                    binding.clContent.visible()
                    binding.lvLoading.gone()
                    binding.evError.gone()
                    binding.ncNoConnection.gone()
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
            handleViewStateErrorWithoutItems()
        } else {
            handleViewStateErrorWithItems()
        }
    }

    private fun ViewState.Error<*>.handleViewStateErrorWithoutItems() {
        binding.clContent.gone()
        binding.lvLoading.gone()

        when (this.error) {
            is NoInternetConnection -> {
                binding.ncNoConnection.visible()
                binding.evError.gone()
            }
            else -> {
                binding.ncNoConnection.gone()
                binding.evError.visible()
            }
        }
    }

    private fun ViewState.Error<*>.handleViewStateErrorWithItems() {
        binding.clContent.visible()
        binding.lvLoading.gone()
        binding.evError.gone()

        sharedViewModel.showRefreshButton.postValue(true)
        adapter.removeLoading()

        val message = when (this.error) {
            is NoInternetConnection -> {
                getString(R.string.setlist_list_error_in_next_pages_without_internet_connection)
            }
            else -> {
                getString(R.string.setlist_list_error_in_next_pages)
            }
        }

        Snackbar.make(binding.clContent, message, Snackbar.LENGTH_LONG).show()
    }

    private fun initList() {
        with (binding.rvList) {
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
