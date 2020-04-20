package com.josuearevalodev.persistence.datasource

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDataSource
import com.josuearevalodev.domain.entities.ArtistEntity
import com.josuearevalodev.domain.entities.ArtistSetlistsResponse
import com.josuearevalodev.persistence.db.SetlistFmDao
import com.josuearevalodev.persistence.db.SetlistFmDatabase
import com.josuearevalodev.persistence.mapper.mapToArtistEntity
import com.josuearevalodev.persistence.mapper.mapToDatabaseArtistEntity
import io.reactivex.Completable
import io.reactivex.Single

class DatabaseSetlistFmDataSourceImpl(private val dbDao: SetlistFmDao) : SetListFmDataSource {

    override fun getArtist(artistName: String): Single<ArtistEntity> {
        // TODO
        return dbDao
            .getArtist(artistName)
            .map { it.mapToArtistEntity }
            //.map { it.map { it.mapToArtistEntity } }

        //return Single.just(listOf())
    }

    override fun getArtistSetlists(artistId: String, page: Int): Single<ArtistSetlistsResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*override fun insertArtist(artist: ArtistEntity): Completable {
        return dbDao.insertArtist(artist.mapToDatabaseArtistEntity)
    }*/

    override fun insertArtist(artist: ArtistEntity): Completable {
        return dbDao.insertArtist(artist.mapToDatabaseArtistEntity)
    }
}
