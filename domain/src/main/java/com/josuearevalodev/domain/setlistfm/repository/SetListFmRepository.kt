package com.josuearevalodev.domain.setlistfm.repository

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.ArtistSetlistsResponseEntity
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import io.reactivex.Completable
import io.reactivex.Single

interface SetListFmRepository {

    fun getArtist(artistName: String): Single<ArtistEntity>

    fun getArtistSetlists(artistId: String, page: Int, itemsPerPage: Int): Single<ArtistSetlistsResponseEntity>

    fun getSetlistDetail(setlistId: String): Single<SetlistEntity>

    fun updateArtistWithSetlistsHeaderData(idArtist: String, itemsPerPage: Int, totalSetlists: Int, lastPage1RemoteCall: Long = -1L): Completable
}
