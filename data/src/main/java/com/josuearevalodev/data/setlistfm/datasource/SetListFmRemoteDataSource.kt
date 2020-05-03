package com.josuearevalodev.data.setlistfm.datasource

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponse
import io.reactivex.Single

interface SetListFmRemoteDataSource {

    fun getArtist(artistName: String): Single<ArtistEntity>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
