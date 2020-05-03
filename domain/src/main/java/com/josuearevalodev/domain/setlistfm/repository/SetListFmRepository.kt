package com.josuearevalodev.domain.setlistfm.repository

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponse
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import io.reactivex.Single

interface SetListFmRepository {

    fun getArtist(artistName: String): Single<ArtistEntity>

    fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse>

    fun getSetlistDetail(setlistId: String): Single<SetlistEntity>
}
