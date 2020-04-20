package com.josuearevalodev.data.setlistfm.datasource

import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.entities.SearchArtistsResponse
import io.reactivex.Completable
import io.reactivex.Single

interface SetListFmDataSource {

    fun getArtist(artistName: String): Single<ArtistEntity>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>

    fun insertArtist(artist: ArtistEntity): Completable {
        return Completable.complete()
    }
}
