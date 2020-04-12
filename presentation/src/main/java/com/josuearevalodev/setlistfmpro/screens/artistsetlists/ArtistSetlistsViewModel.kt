package com.josuearevalodev.setlistfmpro.screens.searchartists

interface ArtistSetlistsViewModel {

    var artistName: String

    fun searchSetlists(idArtist: String, page: Int)
}