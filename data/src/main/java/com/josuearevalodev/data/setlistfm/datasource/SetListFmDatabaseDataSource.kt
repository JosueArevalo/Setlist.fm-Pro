package com.josuearevalodev.data.setlistfm.datasource

import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import io.reactivex.Completable
import io.reactivex.Single

interface SetListFmDatabaseDataSource {

    fun getArtist(artistName: String): Single<ArtistEntity>

    fun getArtistSetlists(artistId: String, page: Int, itemsPerPage: Int): Single<List<SetlistEntity>>

    fun getSetlistDetail(setlistId: String): Single<SetlistEntity>

    fun insertArtist(artist: ArtistEntity): Completable

    fun insertSetlists(setlists: List<SetlistEntity>): Completable

    fun updateArtistWithSetlistsHeaderData(idArtist: String, itemsPerPage: Int, totalSetlists: Int): Completable
}
