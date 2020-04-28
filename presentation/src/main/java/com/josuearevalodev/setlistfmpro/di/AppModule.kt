package com.josuearevalodev.setlistfmpro.di

import com.josuearevalodev.setlistfmpro.screens.artistsetlists.ArtistSetlistsViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.ArtistSetlistsViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.list.ArtistSetlistsListAdapter
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.list.ArtistSetlistsListViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.list.ArtistSetlistsListViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.searchartists.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    // SearchArtists (Activity)
    viewModel { SearchArtistsViewModelImpl() } bind SearchArtistsViewModel::class

    // Artist Setlists (Activity)
    viewModel { ArtistSetlistsListViewModelImpl(get(), get()) } bind ArtistSetlistsListViewModel::class

    // Artist Setlists List (Fragment)
    viewModel { ArtistSetlistsViewModelImpl() } bind ArtistSetlistsViewModel::class

    factory { ArtistSetlistsListAdapter() } bind ArtistSetlistsListAdapter::class

    // Artist Setlists Map (Fragment)

    // Artist Setlists Detail (Fragment)
    viewModel { SetlistDetailViewModelImpl(get()) } bind SetlistDetailViewModel::class

}
