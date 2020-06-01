package com.josuearevalodev.setlistfmpro.di

import com.josuearevalodev.setlistfmpro.commons.PermissionRequester
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.container.ArtistSetlistsViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.container.ArtistSetlistsViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection.ArtistSetlistsListAdapter
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection.ArtistSetlistsListViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection.ArtistSetlistsListViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModel
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.shared.ArtistSetlistsSharedViewModelImpl
import com.josuearevalodev.setlistfmpro.screens.setlistdetail.SetlistDetailViewModel
import com.josuearevalodev.setlistfmpro.screens.setlistdetail.SetlistDetailViewModelImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {


    // Artist Setlists (Activity)
    viewModel { ArtistSetlistsViewModelImpl(get()) } bind ArtistSetlistsViewModel::class

    viewModel { ArtistSetlistsSharedViewModelImpl() } bind ArtistSetlistsSharedViewModel::class

    // Artist Setlists List (Fragment)
    viewModel { ArtistSetlistsListViewModelImpl(get(), get()) } bind ArtistSetlistsListViewModel::class

    factory { ArtistSetlistsListAdapter() } bind ArtistSetlistsListAdapter::class

    // Artist Setlists Detail (Fragment)
    viewModel { SetlistDetailViewModelImpl(get()) } bind SetlistDetailViewModel::class

    single { PermissionRequester(get()) }

}
