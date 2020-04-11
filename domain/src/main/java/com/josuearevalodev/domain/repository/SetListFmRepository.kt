package com.josuearevalodev.domain.repository

import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import io.reactivex.Single
import com.josuearevalodev.domain.entities.SearchArtistsResponse

interface SetListFmRepository {

    fun getArtists(artistName: String): Single<SearchArtistsResponse>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
