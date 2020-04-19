package com.josuearevalodev.usecases.di

import com.josuearevalodev.usecases.setlists.getartistsetlists.GetArtistSetlists
import com.josuearevalodev.usecases.setlists.getartistsetlists.GetArtistSetlistsImpl
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistByName
import com.josuearevalodev.usecases.setlists.searchartistsbyname.SearchArtistByNameImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val userCaseModule = module {

    factory { GetArtistSetlistsImpl(get()) } bind GetArtistSetlists::class

    factory { SearchArtistByNameImpl(get()) } bind SearchArtistByName::class
}
