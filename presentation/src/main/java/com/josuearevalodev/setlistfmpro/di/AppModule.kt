package com.josuearevalodev.setlistfmpro.di

import com.josuearevalodev.setlistfmpro.screens.artistsetlists.ArtistSetlistAdapter
import com.josuearevalodev.setlistfmpro.screens.searchartists.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchArtistsViewModelImpl() } bind SearchArtistsViewModel::class

    viewModel { ArtistSetlistsViewModelImpl(get(), get()) } bind ArtistSetlistsViewModel::class

    viewModel { SetlistDetailViewModelImpl(get()) } bind SetlistDetailViewModel::class

    factory { ArtistSetlistAdapter() } bind ArtistSetlistAdapter::class
}
