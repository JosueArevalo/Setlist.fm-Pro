package com.josuearevalodev.data.setlistfm.datasource

import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Single

interface SetListFmDataSource {

    fun getArtists(artistName: String): Single<SearchArtistsResponse>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
