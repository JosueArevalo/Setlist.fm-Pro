package com.josuearevalodev.setlistfmpro.screens.searchartists

interface ArtistSetlistsViewModel {

    var artistName: String

    fun searchArtistByName(text: String)

    fun searchSetlists(idArtist: String, page: Int)
}