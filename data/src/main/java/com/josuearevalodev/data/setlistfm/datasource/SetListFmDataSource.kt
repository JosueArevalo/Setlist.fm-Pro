package com.josuearevalodev.data.setlistfm.datasource

import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Single

interface SetListFmDataSource {

    fun getArtists(artistName: String): Single<List<ArtistEntity>>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
