package com.josuearevalodev.usecases.di

import com.josuearevalodev.usecases.setlists.getartistsetlists.GetArtistSetlists
import com.josuearevalodev.usecases.setlists.getartistsetlists.GetArtistSetlistsImpl
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistsByName
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistsByNameImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val userCaseModule = module {

    factory { GetArtistSetlistsImpl(get()) } bind GetArtistSetlists::class

    factory { SearchArtistsByNameImpl(get()) } bind SearchArtistsByName::class
}
