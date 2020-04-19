package com.josuearevalodev.persistence.datasource

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.persistence.db.SetlistFmDao
import com.josuearevalodev.persistence.db.SetlistFmDatabase
import com.josuearevalodev.persistence.mapper.mapToArtistEntity
import io.reactivex.Single

class DatabaseSetlistFmDataSourceImpl(private val dbDao: SetlistFmDao) : SetListFmDataSource {

    override fun getArtists(artistName: String): Single<List<ArtistEntity>> {
        // TODO
        return dbDao
            .getArtists(artistName)
            .map { it.map { it.mapToArtistEntity } }

        //return Single.just(listOf())
    }

    override fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
