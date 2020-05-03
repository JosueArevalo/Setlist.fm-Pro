package com.josuearevalodev.persistence.setlistfm.datasource

import com.josuearevalodev.data.setlistfm.datasource.SetListFmDatabaseDataSource
import com.josuearevalodev.domain.setlistfm.entities.ArtistEntity
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.persistence.setlistfm.db.SetlistFmDao
import com.josuearevalodev.persistence.setlistfm.error.DatabaseError
import com.josuearevalodev.persistence.setlistfm.error.mapToDatabaseError
import com.josuearevalodev.persistence.setlistfm.mapper.mapToArtistEntity
import com.josuearevalodev.persistence.setlistfm.mapper.mapToDatabaseArtistEntity
import com.josuearevalodev.persistence.setlistfm.mapper.mapToDatabaseSetlistEntity
import com.josuearevalodev.persistence.setlistfm.mapper.mapToSetlistEntity
import io.reactivex.Completable
import io.reactivex.Single

class DatabaseSetlistFmDataSourceImpl(private val dbDao: SetlistFmDao) :
    SetListFmDatabaseDataSource {

    override fun getArtist(artistName: String): Single<ArtistEntity> {
        return dbDao
            .getArtist(artistName)
            .onErrorResumeNext {
                Single.error(it.mapToDatabaseError)
            }
            .map { it.mapToArtistEntity }
    }

    override fun getArtistSetlists(artistId: String, page: Int, itemsPerPage: Int): Single<List<SetlistEntity>> {
        val offset = page * itemsPerPage
        return dbDao
            .getArtistSetlists(artistId = artistId, itemsPerPage = itemsPerPage, offset = offset)
            .onErrorResumeNext {
                Single.error(it.mapToDatabaseError)
            }
            .map { setlists ->
                setlists.map { dbSetlistEntity ->
                    dbSetlistEntity.mapToSetlistEntity
                }
            }
            .flatMap { setlists ->
                // TODO: For now, we're returning this exception if results are not found.
                // Check later if this logic has to be done outside
                if (setlists.isNotEmpty()) {
                    Single.just(setlists)
                } else {
                    Single.error(DatabaseError.NoResultsFound(Throwable()))
                }
            }
    }

    override fun insertArtist(artist: ArtistEntity): Completable {
        return dbDao.insertArtist(artist.mapToDatabaseArtistEntity)
    }

    override fun insertSetlists(setlists: List<SetlistEntity>): Completable {
        return dbDao.insertSetlists(setlists.map { it.mapToDatabaseSetlistEntity })
    }

    override fun getSetlistDetail(setlistId: String): Single<SetlistEntity> {
        return dbDao.getSetlist(setlistId)
            .onErrorResumeNext {
                Single.error(it.mapToDatabaseError)
            }
            .map {
                it.mapToSetlistEntity
            }
    }

    override fun updateArtistWithSetlistsHeaderData(idArtist: String, itemsPerPage: Int, totalSetlists: Int): Completable {
        return dbDao.updateArtistWithSetlistsHeaderData(
            idArtist = idArtist,
            itemsPerPage = itemsPerPage,
            totalSetlists = totalSetlists
        )
    }
}
