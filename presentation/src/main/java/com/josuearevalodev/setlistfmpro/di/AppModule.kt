package com.josuearevalodev.setlistfmpro.di

import com.josuearevalodev.setlistfmpro.screens.artistsetlists.container.ArtistSetlistsViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.container.ArtistSetlistsViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection.ArtistSetlistsListAdapter
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection.ArtistSetlistsListViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection.ArtistSetlistsListViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.searchartists.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    // SearchArtists (Activity)
    viewModel { SearchArtistsViewModelImpl() } bind SearchArtistsViewModel::class

    // Artist Setlists (Activity)
    viewModel { ArtistSetlistsListViewModelImpl(get(), get()) } bind ArtistSetlistsListViewModel::class

    viewModel { ArtistSetlistsSharedViewModelImpl() } bind ArtistSetlistsSharedViewModel::class

    // Artist Setlists List (Fragment)
    viewModel { ArtistSetlistsViewModelImpl() } bind ArtistSetlistsViewModel::class

    factory { ArtistSetlistsListAdapter() } bind ArtistSetlistsListAdapter::class

    // Artist Setlists Map (Fragment)

    // Artist Setlists Detail (Fragment)
    viewModel { SetlistDetailViewModelImpl(get()) } bind SetlistDetailViewModel::class

}
