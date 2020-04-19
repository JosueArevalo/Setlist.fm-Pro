package com.josuearevalodev.domain.repository

import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import io.reactivex.Single

interface SetListFmRepository {

    fun getArtists(artistName: String): Single<List<ArtistEntity>>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
