package com.josuearevalodev.domain.repository

import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import io.reactivex.Single

interface SetListFmRepository {

    fun getArtist(artistName: String): Single<ArtistEntity>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>
}
