package com.josuearevalodev.setlistfmpro.di

import com.josuearevalodev.setlistfmpro.screens.artistsetlists.ArtistSetlistAdapter
import com.josuearevalodev.setlistfmpro.screens.searchartists.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchArtistsViewModelImpl(get()) } bind SearchArtistsViewModel::class

    viewModel { ArtistSetlistsViewModelImpl() } bind ArtistSetlistsViewModel::class

    viewModel { SetlistDetailViewModelImpl() } bind SetlistDetailViewModel::class

    factory { ArtistSetlistAdapter() } bind ArtistSetlistAdapter::class
}
